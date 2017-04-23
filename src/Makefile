CC=gcc
CFLAGS=-Wall -pedantic -ansi -Werror -O3 -m64
TARGET=main

all: $(TARGET)
	@echo Success!

$(TARGET):
	$(CC) $(CFLAGS) -o $@ $(@:=.c)
