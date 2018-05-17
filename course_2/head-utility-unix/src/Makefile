CC=gcc
CFLAGS=-Wall -pedantic -ansi -Werror -O3 -m64
TARGET=main
OUT=head

all: $(TARGET)
	@echo Success!

$(TARGET):
	$(CC) $(CFLAGS) -o $(OUT) $(@:=.c)
