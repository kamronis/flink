package org.apache.flink.streaming.connectors.kafka.table;

import org.apache.flink.connector.base.source.reader.RecordEvaluator;

/** {@link RecordEvaluator} for kafka table tests. Stops input when maxRecords is reached. */
public class KafkaTableTestRecordEvaluator<RowData> implements RecordEvaluator<RowData> {

    public static int maxRecords = 3;
    private int count = 0;

    @Override
    public boolean isEndOfStream(RowData record) {
        count++;
        return count == maxRecords;
    }
}
