package com.gobinda.sample.utils.rx;

import io.reactivex.Scheduler;

/**
 * Scheduler used in RxJava
 */

public interface SchedulerProvider {

    Scheduler ui();

    Scheduler computation();

    Scheduler io();

}
