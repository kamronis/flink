package org.apache.flink.connector.base.table.util;

import org.apache.flink.configuration.ReadableConfig;
import org.apache.flink.connector.base.source.reader.RecordEvaluator;
import org.apache.flink.table.data.RowData;

import java.util.Optional;

import static org.apache.flink.table.factories.FactoryUtil.RECORD_EVALUATOR;

/** Utilities for connector table options in connectors. */
public class ConnectorOptionsUtil {

    public static Optional<RecordEvaluator<RowData>> getFlinkRecordEvaluator(
            ReadableConfig tableOptions) {
        final Optional<String> recordEvaluatorClass = tableOptions.getOptional(RECORD_EVALUATOR);
        if (recordEvaluatorClass.isPresent()) {
            try {
                return Optional.of(
                        ((RecordEvaluator<RowData>)
                                RecordEvaluator.class
                                        .getClassLoader()
                                        .loadClass(recordEvaluatorClass.get())
                                        .newInstance()));
            } catch (InstantiationException | ClassNotFoundException | IllegalAccessException e) {
                throw new RuntimeException(
                        String.format(
                                "Could not create record evaluator with class %s",
                                recordEvaluatorClass.get()),
                        e);
            }
        }
        return Optional.empty();
    }
}
