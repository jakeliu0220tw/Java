/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package test;

import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.source.SourceFunction;
import org.apache.flink.walkthrough.common.sink.AlertSink;
import org.apache.flink.walkthrough.common.entity.Alert;
import org.apache.flink.walkthrough.common.entity.Transaction;
import org.apache.flink.walkthrough.common.source.TransactionSource;

/**
 * Skeleton code for the datastream walkthrough
 */
public class FraudDetectionJob {
	public static void main(String[] args) throws Exception {

		final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

		env.addSource(new SourceFunction<Long>() {
			
			private long count = 0L;
			private volatile boolean isRunning = true;
		
			public void run(SourceContext<Long> ctx) {
				while (isRunning && count < 1000) {
					// 通过collect将输入发送出去 
					ctx.collect(count);
					count++;
				}
			}
		
			public void cancel() {
				isRunning = false;
			}
		
		}).print();
		env.execute("test");
		


/*
		StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

		// --format path --input /tmp/job/roundToElasticsearch.txt
		// --format url  --input 10.0.0.1/conf/roundToElasticsearch
		// --format text --input yaml or json string

		// TODO 取得傳入的參數(url, file, text...)
		if(path){
			new File(path) to string;
		} else if(url){
			http.get(url).getBody();
		} else if (text){
			text
		}

		// TODO 解析Sources Config, getMap
		config to Object and map

		// TODO SourceFactory

		DataStream<Transaction> transactions = env
			.addSource(new TransactionSource())
			.name("transactions");

		DataStream<Alert> alerts = transactions
			.keyBy(Transaction::getAccountId)
			.process(new FraudDetector())
			.name("fraud-detector");

		// TODO 解析Sink Config, getMap

		// TODO SinkFactory

		alerts
			.addSink(new AlertSink())
			.name("send-alerts");

		env.execute("Fraud Detection");
*/
	}
}
