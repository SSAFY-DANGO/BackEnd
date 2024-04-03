package com.dango.dango.global.kafka;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import lombok.RequiredArgsConstructor;

@EnableKafka
@Configuration
@RequiredArgsConstructor
public class KafkaConsumerConfig {
	private final Environment env;


	@Bean
	public Map<String,Object> consumerConfig(){
		Map<String,Object> props = new HashMap<>();

		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,env.getProperty("spring.kafka.consumer.bootstrap-servers"));
		props.put(ConsumerConfig.GROUP_ID_CONFIG,env.getProperty("spring.kafka.consumer.group-id"));
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,StringDeserializer.class);
		return props;
	}

	@Bean
	public ConsumerFactory<String,String> consumerFactory(){
		return new DefaultKafkaConsumerFactory<>(this.consumerConfig());
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String,String> kafkaListenerContainerFactory(){
		ConcurrentKafkaListenerContainerFactory<String,String>factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(this.consumerFactory());
		return factory;
	}
}
