package com.example.JournalApp.service;

import java.util.stream.Stream;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import com.example.JournalApp.entity.Users;

public class UserArgumentProvider implements ArgumentsProvider {

	@Override
	public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {

		return Stream.of(
				Arguments.of(Users.builder().userName("Kamati").password("Ramu").build()),
				Arguments.of(Users.builder().userName("Rahi").password("Pavan").build())
				);
	}

}

