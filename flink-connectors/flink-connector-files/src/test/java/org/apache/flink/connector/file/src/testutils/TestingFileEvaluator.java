package org.apache.flink.connector.file.src.testutils;

import org.apache.flink.connector.base.source.reader.RecordEvaluator;

/** {@link RecordEvaluator} for file tests. Stops input when maxRecords is reached. */
public class TestingFileEvaluator<String> implements RecordEvaluator<String> {

    private final int maxRecords;
    private int count = 0;

    public TestingFileEvaluator(int maxRecords) {
        this.maxRecords = maxRecords;
    }

    @Override
    public boolean isEndOfStream(String record) {
        count++;
        return count == maxRecords;
    }
}
