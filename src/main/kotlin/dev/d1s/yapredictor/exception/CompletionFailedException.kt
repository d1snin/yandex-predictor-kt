package dev.d1s.yapredictor.exception

public class CompletionFailedException(message: String) : RuntimeException("Completion failed: $message")