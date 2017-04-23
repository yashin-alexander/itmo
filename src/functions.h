#ifndef HEAD_FUCTIONS_H
#define HEAD_FUCTIONS_H

static void print_options_error(const char* string, int err_buffer);
static void read_stdin(int number_of_strings);
static void read_file_tostrings(int descriptor,int number_of_strings);
static void options_procesing(const char* string,char **names,int argc);
static void print_bfiles_options_after(char **names, int file_headers, int pre_number, int argc, int plus);
static void print_files_options_after(char **names, int file_headers, int pre_number, int argc,int plus);
static void read_byte_stdin(int number_of_symbols);
static void read_file_tobytes(int descriptor,int number_of_strings);

#endif
