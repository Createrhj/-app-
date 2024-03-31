package base;

public class SnowFlake {

    private final long START_TIME_STAMP = 1420041600000L;

    private final long SEQUENCE_BIT = 12L;
    private final long MACHINE_BIT = 5L;
    private final long DATACENTER_BIT = 5L;
    private final long MAX_DATACENTER_NUM = -1L ^ (-1L << DATACENTER_BIT);
    private final long MAX_MACHINE_NUM = -1L ^ (-1L << MACHINE_BIT);
    private final long MAX_SEQUENCE = -1L ^ (-1L << SEQUENCE_BIT);
    private final long MACHINE_ID_LEFT = SEQUENCE_BIT;
    private final long DATACENTER_ID_LEFT = SEQUENCE_BIT + MACHINE_BIT;
    private final long TIME_STAMP_LEFT = SEQUENCE_BIT + MACHINE_BIT + DATACENTER_BIT;
    private long datacenterId;
    private long machineId;
    private long sequence = 0L;
    private long lastTimestamp = -1L;

    public SnowFlake(long machineId, long datacenterId) {
        if (machineId > MAX_MACHINE_NUM || machineId < 0) {
            throw new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0", MAX_MACHINE_NUM));
        }
        if (datacenterId > MAX_DATACENTER_NUM || datacenterId < 0) {
            throw new IllegalArgumentException(String.format("datacenter Id can't be greater than %d or less than 0", MAX_DATACENTER_NUM));
        }
        this.machineId = machineId;
        this.datacenterId = datacenterId;
    }

    public synchronized long nextId() {

        long currentTimeStamp = getCurrentTimeStamp();

        if (currentTimeStamp < lastTimestamp) {
            throw new RuntimeException(String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds", lastTimestamp - currentTimeStamp));
        }

        if (currentTimeStamp == lastTimestamp) {

            sequence = (sequence + 1) & MAX_SEQUENCE;

            if (sequence == 0) {

                currentTimeStamp = getNewTimeStamp(lastTimestamp);
            }
        }
        else {
            sequence = 0L;
        }

        lastTimestamp = currentTimeStamp;

        return ((currentTimeStamp - START_TIME_STAMP) << TIME_STAMP_LEFT)
                | (datacenterId << DATACENTER_ID_LEFT)
                | (machineId << MACHINE_ID_LEFT)
                | sequence;
    }



    protected long getCurrentTimeStamp() {
        return System.currentTimeMillis();
    }

    protected long getNewTimeStamp(long lastTimestamp) {
        long timestamp = getCurrentTimeStamp();
        while (timestamp <= lastTimestamp) {
            timestamp = getCurrentTimeStamp();
        }
        return timestamp;
    }

}

