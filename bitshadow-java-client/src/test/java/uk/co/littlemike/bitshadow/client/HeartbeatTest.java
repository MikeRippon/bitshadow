package uk.co.littlemike.bitshadow.client;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import uk.co.littlemike.bitshadow.client.config.BitshadowConfiguration;
import uk.co.littlemike.bitshadow.client.config.PojoBitshadowConfiguration;
import uk.co.littlemike.bitshadow.client.endpoint.BitshadowEndpoint;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class HeartbeatTest {
    private static final long INTERVAL = 5000;
    private static final String INSTANCE_ID = "Instance id";

    @Mock ScheduledExecutorService scheduler;
    @Mock BitshadowEndpoint endpoint;

    Heartbeat heartbeat;

    @Before
    public void setUp() {
        BitshadowConfiguration config = new TestConfiguration().withHeartbeatIntervalMillis(INTERVAL);
        heartbeat = new Heartbeat(scheduler, endpoint, config);
    }

    @Before
    public void schedulerExecutesJobsImmediately() {
        when(scheduler.schedule(any(Runnable.class), eq(INTERVAL), eq(TimeUnit.MILLISECONDS))).thenAnswer(inv -> {
            ((Runnable) inv.getArguments()[0]).run();
            return null;
        });
    }

    @Test
    public void usesConfiguredInterval() {
        heartbeat.start(INSTANCE_ID);

        verify(scheduler).schedule(any(Runnable.class), eq(INTERVAL), eq(TimeUnit.MILLISECONDS));
    }

    @Test
    public void sendsHeartbeatUsingScheduler() {
        heartbeat.start(INSTANCE_ID);

        verify(endpoint).heartbeat(INSTANCE_ID);
    }

    @Test
    public void hasInitialStatusOfPending() {
        assertThat(heartbeat.getStatus()).isEqualTo(HeartbeatStatus.PENDING);
    }

    @Test
    public void hasStatusOfOkAfterSuccessfulHeartbeat() {
        when(endpoint.heartbeat(INSTANCE_ID)).thenReturn(true);

        heartbeat.start(INSTANCE_ID);

        assertThat(heartbeat.getStatus()).isEqualTo(HeartbeatStatus.OK);
    }

    @Test
    public void hasStatusOfFailedAfterFailedHeartbeat() {
        when(endpoint.heartbeat(any())).thenReturn(false);

        heartbeat.start(INSTANCE_ID);

        assertThat(heartbeat.getStatus()).isEqualTo(HeartbeatStatus.FAILED);
    }

    @Test
    public void hasStatusOfFailedAfterHeartbeatThrowsException() {
        doThrow(new RuntimeException("Oh no")).when(endpoint).heartbeat(INSTANCE_ID);

        heartbeat.start(INSTANCE_ID);

        assertThat(heartbeat.getStatus()).isEqualTo(HeartbeatStatus.FAILED);
    }

    @Test
    public void shutsDownSchedulerImmediatelyWhenStopped() {
        heartbeat.start(INSTANCE_ID);

        heartbeat.stop();

        verify(scheduler).shutdownNow();
    }
}