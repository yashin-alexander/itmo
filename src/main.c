#include <fcntl.h>
#include <unistd.h>
#include <string.h>
#include <stdlib.h>
#include <stdio.h>
#include <ctype.h>
#include <errno.h>

#include "messages.h"
#include "functions.h"

#define DEFAULT_STRINGSNUM 10 /*default number of the processed strings\bytes */



static void print_options_error(const char* string, int err_number){
    char message[1000] = "";
    if(err_number==1){
        strncat(message, MSG_BYTESNUM, strlen(MSG_BYTESNUM));
        strncat(message, string, strlen(string));
        strncat(message, "\n", 1);

        write(STDERR_FILENO, message, strlen(message));
    }
    else if(err_number==2){
        strncat(message, MSG_NO_BNUMBER, strlen(MSG_NO_BNUMBER));
        strncat(message, "\n", 1);

        write(STDERR_FILENO, message, strlen(message));
    }
    else if(err_number==3){
        strncat(message, MSG_STRSNUM, strlen(MSG_STRSNUM));
        strncat(message, string, strlen(string));
        strncat(message, "\n", 1);

        write(STDERR_FILENO, message, strlen(message));
    }
    else if(err_number==4){
        strncat(message, MSG_NO_SNUMBER, strlen(MSG_NO_SNUMBER));
        strncat(message, "\n", 1);

        write(STDERR_FILENO, message, strlen(message));
    }
    else if(err_number==5){
        strncat(message, MSG_INVALID_OPTION, strlen(MSG_INVALID_OPTION));
        strncat(message, string, strlen(string));
        strncat(message, "\n", 1);

        write(STDERR_FILENO, message, strlen(message));
    }
    else if(err_number==6){
        strncat(message,MSG_INVALID_TRAILING, strlen(MSG_INVALID_TRAILING));
        strncat(message, string, strlen(string));
        strncat(message, "\n", 1);

        write(STDERR_FILENO, message, strlen(message));
    }
    else if(err_number==7){
        strncat(message, MSG_CANNOT_OPEN_FILE, strlen(MSG_CANNOT_OPEN_FILE));
        strncat(message, string, strlen(string));
        strncat(message,"' ",2);
        strncat(message, strerror(errno), strlen(strerror(errno)));
        strncat(message, "\n", 1 );

        write(STDERR_FILENO, message, strlen(message));
    }
    else if(err_number==8){
        strncat(message, MSG_WRONG_OPTION, strlen(MSG_WRONG_OPTION));
        strncat(message, string, strlen(string));
        strncat(message, MSG_WRONG_OPTION2, strlen(MSG_WRONG_OPTION2));
        strncat(message, "\n", 1);

        write(STDERR_FILENO, message, strlen(message));
    }
    else if(err_number==9){
        strncat(message, MSG_BAD_WRITE, strlen(MSG_BAD_WRITE));
        strncat(message, string, strlen(string));
        strncat(message, "' ", 2);
        strncat(message, strerror(errno), strlen(strerror(errno)));
        strncat(message, "\n", 1);

        write(STDERR_FILENO, message, strlen(message));
    }
    else if(err_number==10){
        strncat(message, MSG_LONG_NAME, strlen(MSG_LONG_NAME));
        strncat(message, "\n", 1);

        write(STDERR_FILENO, message, strlen(message));
    }
}

static void read_stdin(int number_of_strings){

    char buffer[BUFSIZ]; /*buffer size initialisation*/
    size_t chars_read;
    size_t chars_wrote;

    char message_buffer[BUFSIZ];
    int i;

    for(i=0;i<number_of_strings;i++) {
        chars_read = read(STDIN_FILENO, buffer, BUFSIZ);
        if (chars_read == -1) {
            print_options_error(message_buffer,7);
            return;
        }
        if (!chars_read)
            return;
        chars_wrote = write(STDOUT_FILENO, buffer, chars_read);
        if (chars_wrote == -1){
            print_options_error(message_buffer,9);
            return;
        }
    }
}
static void read_byte_stdin(int number_of_symbols){
    size_t chars_read;
    size_t chars_wrote;
    char buffer[BUFSIZ]; /*buffer size initialisation*/

    int i;

    for(i=0;i<number_of_symbols;i++) {
        chars_read = read(STDIN_FILENO, buffer, 1);
        if (chars_read == -1) {
            print_options_error(buffer,7);
            return;
        }
        if (!chars_read)
            return;
        chars_wrote = write(STDOUT_FILENO, buffer, 1);
        if (chars_wrote == -1){
            print_options_error(buffer,9);
            return;
        }
    }
}

static void read_file_tostrings(int descriptor,int number_of_strings) {
    char buffer[BUFSIZ];
    int i;
    int read_counter;

    while (number_of_strings) {
        if ((read_counter = read(descriptor, buffer, sizeof(buffer))) == 0)
            break;

        if (read_counter < 0) {
            print_options_error("", 7);
            exit(0);
        }
        for (i = 0; i < read_counter; ++i) {
            if (buffer[i] == '\n')
                number_of_strings--;
            if (!number_of_strings)
                break;
        }
        if (buffer[i] == '\n')
            i++;
        if (write(STDOUT_FILENO, buffer, i) < 0) {
            print_options_error("", 9);
            exit(0);
        }
    }
}
static void read_file_tobytes(int descriptor,int number_of_bytes){
    char buffer[BUFSIZ];
    int ctr_ent;

    while((ctr_ent=read(descriptor, buffer, BUFSIZ))){
        if(number_of_bytes>=ctr_ent){
            write(STDOUT_FILENO,buffer,ctr_ent);
            number_of_bytes-=ctr_ent;
        }else{
            write(STDOUT_FILENO,buffer,number_of_bytes);
            break;
        }
        if(errno) {
            print_options_error(buffer,7);
            exit (1);
        }
    }
}

static void print_bfiles_options_after(char **names, int file_headers, int number_of_symbols, int argc,int plus){
    int descriptor;
    int i;
    if (number_of_symbols==0)
        number_of_symbols=DEFAULT_STRINGSNUM;
    if (argc<=2){
        if (file_headers)
            write(STDOUT_FILENO, "\n==> standard input <==\n", 24);
        read_byte_stdin(number_of_symbols);
        exit(0);
    }
    for (i=2+plus; i<argc; i++) {
        if(names[i][0]=='-'&&strlen(names[i])==1){
            if(file_headers)
                write(STDOUT_FILENO, "\n==> standard input <==\n", 24);
            read_byte_stdin(number_of_symbols);
            continue;
        }

        if(strlen(names[i])>255){
            print_options_error(names[i],10);
            continue;
        }

        descriptor = open(names[i], O_RDONLY);

        if (descriptor == -1) {
            print_options_error(names[i],7);
            continue;
        } else {
            if(i!=2+plus&&errno==NULL)
                write(STDOUT_FILENO, "\n", 1);
            if (file_headers) {
                write(STDOUT_FILENO, "======> ", 8);
                write(STDOUT_FILENO, names[i], strlen(names[i]));
                write(STDOUT_FILENO, " <======\n", 9);
            }
            read_file_tobytes(descriptor, number_of_symbols);
        }
    }
}
static void print_files_options_after(char **names, int file_headers, int number_of_strings, int argc, int plus){

    int descriptor;
    int i;
    if (number_of_strings==0)
        number_of_strings=DEFAULT_STRINGSNUM;
    if (argc<=2){
        if (file_headers)
            write(STDOUT_FILENO, "==> standard input <==\n", 24);
        read_stdin(number_of_strings);
        exit(0);
    }
    for (i=2+plus; i<argc; i++) {
        if(names[i][0]=='-'&&strlen(names[i])==1){
            if (file_headers)
                write(STDOUT_FILENO, "==> standard input <==\n", 24);
            read_stdin(number_of_strings);
            continue;
        }
        if(strlen(names[i])>255){
            print_options_error(names[i],10);
            continue;
        }


        descriptor = open(names[i], O_RDONLY);

        if (descriptor == -1 ) {
            print_options_error(names[i],7);
            continue;
        } else {
            if(i!=2+plus&&errno==NULL)
                write(STDOUT_FILENO, "\n", 1);
            if (file_headers) {
                write(STDOUT_FILENO, "======> ", 8);
                write(STDOUT_FILENO, names[i], strlen(names[i]));
                write(STDOUT_FILENO, " <======\n", 9);
            }
            read_file_tostrings(descriptor, number_of_strings);
        }
    }
}




static void options_procesing(const char* string,char **names,int argc){
    int i;
    int number_of_smth=strlen(string);
    int number_of_strings;
    int file_headers = 0;
    int o;

    for (i=1; i < number_of_smth; i++){
        if(string[i]>='0'&&string[i]<='9'){
            if(argc>3)
                file_headers=1;
            i++;
            for(;i<number_of_smth;i++){
                if( string[i]=='v') {
                    file_headers = 1;
                }
                else if( string[i]=='q') {
                    file_headers = 0;
                }
                else if( string[i]=='c') {
                    i++;
                    for (; i < number_of_smth; i++) {
                        if (string[i] != 'q' && string[i] != 'v') {
                            print_options_error(string + 2 * sizeof(char), 1);
                            exit(1);
                        }
                        if (string[i] == 'q')
                            file_headers=0;
                        if (string[i] == 'v')
                            file_headers=1;
                    }
                    number_of_strings=atoi(&string[1]);
                    /*GO PRINT -23432cqvqvq
                    GO PRINT -23432c*/
                    print_bfiles_options_after(names,file_headers,number_of_strings,argc,0);
                    exit(0);
                }

                else if(string[i]>='0'&&string[i]<='9'){
                }
                else{
                    print_options_error(string,6);
                    exit(1);
                }
            }
            number_of_strings=atoi(&string[1]);
            print_files_options_after(names,file_headers,number_of_strings,argc,0);
            /*GO PRINT -3434 file file file*/
            exit(0);
        }
        if(argc>3)
            file_headers=1;
        switch(string[i]){
            case 'v':
                number_of_strings=atoi(&string[2]);
                file_headers=1;
                if(i<number_of_smth) {
                    for (; i < number_of_smth; i++) {
                        if (isdigit(string[i]) != 0) {
                            print_options_error(string + 2 * sizeof(char), 5);
                            exit(1);
                        }
                        if (string[i] == 'q')
                            file_headers=0;
                        if (string[i] == 'v')
                            file_headers=1;
                        if (string[i] == 'n'){
                            i++;

                            number_of_strings=atoi(&string[i]);
                            for (; i < number_of_smth; i++) {
                                if (string[i] < '0' && string[i] > '9') {
                                    print_options_error(string + 2 * sizeof(char), 1);
                                    exit(1);
                                }
                            }
                            print_files_options_after(names,file_headers,number_of_strings,argc,0);
                            exit(0);
                        }
                        if(string[i] == 'c'){
                            i++;
                            number_of_strings=atoi(&string[i]);
                            for (; i < number_of_smth; i++) {
                                if (string[i] < '0' || string[i] > '9') {
                                    print_options_error(string + 2 * sizeof(char), 1);
                                    exit(1);
                                }
                            }
                            print_bfiles_options_after(names,file_headers,number_of_strings,argc,0);
                            exit(0);
                        }
                    }
                }
                print_files_options_after(names,file_headers,number_of_strings,argc,0);
                exit(0);
                /*GO PRINT -v123*/
            case 'q':
                number_of_strings=atoi(&string[2]);
                file_headers=0;
                if(i<number_of_smth) {
                    for (; i < number_of_smth; i++) {
                        if (isdigit(string[i]) != 0 ) {
                            print_options_error(string+2*sizeof(char),5);
                            exit(1);
                        }
                        if (string[i] == 'q')
                            file_headers=0;
                        if (string[i] == 'v')
                            file_headers=1;
                        if (string[i] == 'n'){
                            i++;
                            number_of_strings=atoi(&string[i]);
                            for (; i < number_of_smth; i++) {
                                if (string[i] < '0' && string[i] > '9') {
                                    print_options_error(string + 2 * sizeof(char), 1);
                                    exit(1);
                                }
                            }
                            print_files_options_after(names,file_headers,number_of_strings,argc,0);
                            exit(0);
                        }
                        if(string[i] == 'c'){
                            i++;
                            number_of_strings=atoi(&string[i]);
                            for (; i < number_of_smth; i++) {
                                if (string[i] < '0' || string[i] > '9') {
                                    print_options_error(string + 2 * sizeof(char), 1);
                                    exit(1);
                                }
                            }
                            print_bfiles_options_after(names,file_headers,number_of_strings,argc,0);
                            exit(0);
                        }
                    }
                    print_files_options_after(names,file_headers,number_of_strings,argc,0);
                    exit(0);
                    /*GO PRINT -q123*/
                }else{
                    print_options_error(string,2);
                    exit(1);
                }
                break;
            case 'c':
                i++;
                if(i<number_of_smth) {
                    for (; i < number_of_smth; i++) {
                        if (isdigit(string[i]) == 0) {
                            print_options_error(string+2*sizeof(char),1);
                            exit(1);
                        }
                        if(string[i] == 'v')
                            file_headers=1;
                        if(string[i] == 'q')
                            file_headers=0;
                    }
                    number_of_strings=atoi(&string[2]);
                    print_bfiles_options_after(names,file_headers,number_of_strings,argc,0);
                    exit(0);
                    /*GO PRINT -c123*/
                }else{
                    if(argc>=2&&isdigit(names[2][0])){

                        for(o=0;o<strlen(names[2]);o++) {
                            if (names[2][o] >= '0' && names[2][o] <= '9')
                                continue;
                            print_options_error(string,8);
                            exit(1);
                        }

                        number_of_strings=atoi(names[2]);

                        if(argc==3){
                            read_stdin(number_of_strings);
                            exit(0);
                        }

                        print_bfiles_options_after(names,file_headers,number_of_strings,argc,1);
                        exit(0);
                    }
                    print_options_error(string,2);
                    exit(1);
                }
                break;

            case 'n':
                i++;
                if(i<number_of_smth) {
                    for (; i < number_of_smth; i++) {
                        if (isdigit(string[i]) == 0) {
                            print_options_error(string+2*sizeof(char),3);
                            exit(1);
                        }
                        if(string[i] == 'v')
                            file_headers=1;
                        if(string[i] == 'q')
                            file_headers=0;
                    }
                    number_of_strings=atoi(&string[2]);
                    print_files_options_after(names,file_headers,number_of_strings,argc,0);
                    /*GO PRINT -n123 */
                    exit(0);
                }else{
                    if(argc>=2&&isdigit(names[2][0])){

                        for(o=0;o<strlen(names[2]);o++) {
                            if (names[2][o] >= '0' && names[2][o] <= '9')
                                continue;
                            print_options_error(string,8);
                            exit(1);
                        }

                        number_of_strings=atoi(names[2]);

                        if(argc==3){
                            read_stdin(number_of_strings);
                            exit(0);
                        }

                        print_files_options_after(names,file_headers,number_of_strings,argc,1);
                        exit(0);
                    }
                    print_options_error(string,4);
                    exit(1);
                }
                break;
            case '-':
                if(string[i-1]=='-'&&string[i+1]=='\0'){
                    read_stdin(DEFAULT_STRINGSNUM);
                    exit(1);
                }
                if(string[i-1]=='-'&&string[i+1]!='\0'&&string[i+1]!='-'){
                    print_options_error(string,8);
                    exit(1);
                }
                break;
            default:
                print_options_error(string+sizeof(char),5);
                exit(1);
        }
    }
    /*GO PRINT -q32 -v2*/
    number_of_strings=atoi(&string[2]);
    print_files_options_after(names,file_headers,number_of_strings,argc,0);
}

int main(int argc, char **argv) {
    char return_value = 0;
    int descriptor;
    int options_status = 0;
    int i;


    if(argc == 1){
        read_stdin(DEFAULT_STRINGSNUM);
        exit (return_value);
    }
    for (i = 1; i < argc; i++){
        if(argv[i][0] == '-' ){
            if (argv[i][1]=='\0'){
                read_stdin(DEFAULT_STRINGSNUM);
                options_status=1;
                continue;
            }
            if(!options_status){
                options_procesing(argv[1],argv,argc);
                break;
            }
            continue;
        }

        options_status = 1;
        descriptor = open(argv[i],O_RDONLY);
        if(descriptor == -1){
            print_options_error(argv[i],7);
            return_value = 1;
            continue;
        }
        if (argc>2) {
            write(STDOUT_FILENO, "======> ", 8);
            write(STDOUT_FILENO, argv[i], strlen(argv[i]));
            write(STDOUT_FILENO, " <======\n", 9);
        }
        read_file_tostrings(descriptor,DEFAULT_STRINGSNUM);
    }
    exit (return_value);
}
