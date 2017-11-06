package com.cppba.service;

import com.cppba.util.ListUtils;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author winfed
 * @create 2017-11-06 14:59
 */
@Slf4j
@Service
public class ThreadPoolService {

    private final ThreadPoolTaskExecutor threadPoolTaskExecutor;

    // 任务分解成多少个线程
    private final static Integer allThreadCount = 50;

    @Autowired
    public ThreadPoolService(ThreadPoolTaskExecutor threadPoolTaskExecutor) {
        this.threadPoolTaskExecutor = threadPoolTaskExecutor;
    }

    public String doTest() {
        ArrayList<Integer> allTaskList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            allTaskList.add(i);
        }
        // 任务完成量计算器，线程安全
        AtomicInteger completeCount = new AtomicInteger(0);
        // 任务分解
        List<List<Integer>> taskLists = ListUtils.averageAssign(allTaskList, allThreadCount);

        HashMap<String, FutureTask<Boolean>> FutureTaskMap = new HashMap<>();
        log.warn("任务平均分成{}个线程跑，平均每个线程任务量为{}到{}个组织", allThreadCount, (allTaskList.size() / allThreadCount), (allTaskList.size() / allThreadCount + 1));
        for (int i = 0; i < taskLists.size(); i++) {
            if (taskLists.get(i).size() > 0) {
                FutureTask<Boolean> futureTask = new FutureTask<>(new ThreadPoolTack("ThreadPoolTack[" + i + "]", completeCount, taskLists.get(i)));
                FutureTaskMap.put("ThreadPoolTack[" + i + "]", futureTask);
                // 线程池
                threadPoolTaskExecutor.execute(futureTask);
            }
        }

        // 等到子线程执行完毕，统计执行结果
        Integer successTask = 0, failureTask = 0, exceptionTask = 0;
        for (Map.Entry<String, FutureTask<Boolean>> futureTaskEntry : FutureTaskMap.entrySet()) {
            try {
                Boolean isSuccess = futureTaskEntry.getValue().get();
                if (isSuccess) {
                    successTask++;
                } else {
                    failureTask++;
                }
            } catch (InterruptedException | ExecutionException e) {
                log.error("", e.getMessage());
                exceptionTask++;
            }
        }
        String result = String.format("所有线程执行完毕,成功执行%d条记录,成功执行%d个线程，失败执行%d个线程，异常执行%d个线程！", allTaskList.size(), successTask, failureTask, exceptionTask);
        log.warn(result);
        return result;
    }

    // 任务分解
    @Data
    public class ThreadPoolTack implements Callable<Boolean> {

        private String name;
        private AtomicInteger completeCount;
        private List<Integer> taskList;

        public ThreadPoolTack(String name, AtomicInteger completeCount, List<Integer> taskList) {
            this.name = name;
            this.completeCount = completeCount;
            this.taskList = taskList;
        }

        @Override
        public Boolean call() throws Exception {
            log.warn("线程:[{}]执行开始!", this.getName());
            for (Integer integer : taskList) {
                int count = completeCount.incrementAndGet();
                if (count % 10 == 0) {
                    log.warn("成功执行{}个任务", count);
                }
                log.info("任务{}执行完毕!", integer);
            }
            log.warn("线程:[{}]执行完成!", this.getName());
            return true;
        }
    }
}
