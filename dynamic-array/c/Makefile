CC = cc
CFLAGS = -std=c17 -Wall -Wextra -Wpedantic -g
SANITIZER_FLAGS = -fsanitize=address,undefined -fno-omit-frame-pointer

.PHONY: all test sanitize clean

all: test_dynamic_array

test_dynamic_array: dynamic_array.c dynamic_array.h test_dynamic_array.c
	$(CC) $(CFLAGS) dynamic_array.c test_dynamic_array.c -o $@

test: test_dynamic_array
	./test_dynamic_array

test_dynamic_array_sanitize: dynamic_array.c dynamic_array.h test_dynamic_array.c
	$(CC) $(CFLAGS) $(SANITIZER_FLAGS) dynamic_array.c test_dynamic_array.c -o $@

sanitize: test_dynamic_array_sanitize
	./test_dynamic_array_sanitize

clean:
	$(RM) test_dynamic_array test_dynamic_array_sanitize