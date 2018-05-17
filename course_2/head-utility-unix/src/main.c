#include <fcntl.h>
#include <unistd.h>
#include <string.h>
#include <stdlib.h>
#include <stdio.h>
#include <ctype.h>
#include <errno.h>
#include <limits.h>

#include "messages.h"
#include "functions.h"

#define DEFAULT_STRINGSNUM 10 /*default number of the processed strings\bytes */


static void print_options_error(const char* string, int err_number){
    char message[1000] = "";
    int val = 999;
    switch (err_number) {
        case 1: {
            val -= strlen(MSG_BYTESNUM);
            strncat(message, MSG_BYTESNUM, val);
            val-=strlen(string);
            strncat(message, string, val);
            val-=1;
            strncat(message, "\n", val);

            write(STDERR_FILENO, message, strlen(message));
			break;
        }
        case 2: {
            val-=strlen(MSG_NO_BNUMBER);
            strncat(message, MSG_NO_BNUMBER, val);
            val-=1;
            strncat(message, "\n", val);

            write(STDERR_FILENO, message, strlen(message));
			break;
        }
        case 3: {
            val-=strlen(MSG_STRSNUM);
            strncat(message, MSG_STRSNUM, val);
            val-=strlen(string);
            strncat(message, string, val);
            val-=1;
            strncat(message, "\n", val);

            write(STDERR_FILENO, message, strlen(message));
			break;
        }
        case  4: {
            val-=strlen(MSG_NO_SNUMBER);
            strncat(message, MSG_NO_SNUMBER, val);
            val-=1;
            strncat(message, "\n", val);

            write(STDERR_FILENO, message, strlen(message));
			break;
        }
        case  5: {
            val-=strlen(MSG_INVALID_OPTION);
            strncat(message, MSG_INVALID_OPTION, val);
            val-=strlen(string);
            strncat(message, string, val);
            val-=1;
            strncat(message, "\n", val);

            write(STDERR_FILENO, message, strlen(message));
			break;
        }
        case 6: {
            val-=strlen(MSG_INVALID_OPTION);
            strncat(message, MSG_INVALID_TRAILING, val);
            val-=strlen(string);
            strncat(message, string, val);
            val-=1;
            strncat(message, "\n", val);

            write(STDERR_FILENO, message, strlen(message));
			break;
        }
        case 7 : {
            val-=strlen(MSG_CANNOT_OPEN_FILE);
            strncat(message, MSG_CANNOT_OPEN_FILE, val);
            val-=strlen(string);
            strncat(message, string, val);
            val-=2;
            strncat(message, "' ", val);
            val-=strlen("I/O error");
            strncat(message, "I/O error", val);
            val-=1;
            strncat(message, "\n", val);

            write(STDERR_FILENO, message, strlen(message));
			break;
        }
        case 8: {
            val-=strlen(MSG_WRONG_OPTION);
            strncat(message, MSG_WRONG_OPTION, val);
            val-=strlen(string);
            strncat(message, string, val);
            val-=strlen(MSG_WRONG_OPTION2);
            strncat(message, MSG_WRONG_OPTION2, val);
            val-=1;
            strncat(message, "\n", val);

            write(STDERR_FILENO, message, strlen(message));
		   break;
        }
        case 9: {
            val-=strlen(MSG_BAD_WRITE);
            strncat(message, MSG_BAD_WRITE, val);
            val-=2;
            strncat(message, " '", val);
            val-=strlen(string);
            strncat(message, string, val);
            val-=2;
            strncat(message, "' ", val);
            val-=strlen(strerror(errno));
            strncat(message, strerror(errno), val);
            val-=1;
            strncat(message, "\n", val);

            write(STDERR_FILENO, message, strlen(message));
			break;
        }
        case 10 : {
            val-=strlen(MSG_LONG_NAME);
            strncat(message, MSG_LONG_NAME, val);
            val-=1;
            strncat(message, "\n", val);

            write(STDERR_FILENO, message, strlen(message));
			break;

        }
    }
}

static void read_file_tostrings(int descriptor,int number_of_strings,char* filename) {
    char buffer[BUFSIZ];
    int i;
    int read_counter;

    while (number_of_strings) {
        if ((read_counter = read(descriptor, buffer, sizeof(buffer))) == 0)
            break;
        if (read_counter < 0) {
            print_options_error(filename,7);
            exit(1);
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
            print_options_error(filename,9);
            exit(1);
        }
    }
}
static void read_file_tobytes(int descriptor,int number_of_bytes,char* filename){
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
            print_options_error(filename,7);
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
        read_file_tobytes(STDIN_FILENO, number_of_symbols, "stdin");
        exit(0);
    }
    for (i=2+plus; i<argc; i++) {
        if(names[i][0]=='-'&&strlen(names[i])==1){
            if(file_headers&&argc!=3+plus&&errno==NULL)
                write(STDOUT_FILENO, "==> standard input <==\n", 24);
            read_file_tobytes(STDIN_FILENO, number_of_symbols,"stdin");
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
            read_file_tobytes(descriptor, number_of_symbols,names[i]);
        }
    }
    exit(0);
}
static void print_files_options_after(char **names, int file_headers, int number_of_strings, int argc, int plus){

    int descriptor;
    int i;
    if (number_of_strings==0)
        number_of_strings=DEFAULT_STRINGSNUM;
    if (argc<=2){
        if (file_headers)
            write(STDOUT_FILENO, "==> standard input <==\n", 24);
        read_file_tostrings(STDIN_FILENO, number_of_strings,"stdin");
        exit(0);
    }
    for (i=2+plus; i<argc; i++) {
        if(names[i][0]=='-'&&strlen(names[i])==1){
            if (file_headers&&argc!=3+plus&&errno==NULL)
                    write(STDOUT_FILENO, "==> standard input <==\n", 24);
            read_file_tostrings(STDIN_FILENO, number_of_strings,"stdin");

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
            read_file_tostrings(descriptor, number_of_strings,names[i]);
        }
    }
    exit(0);
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
                    if(number_of_strings<0)
                        number_of_strings=INT_MAX;
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
            if(number_of_strings<0)
                number_of_strings=INT_MAX;
            print_files_options_after(names,file_headers,number_of_strings,argc,0);
            /*GO PRINT -3434 file file file*/
            exit(0);
        }
        if(argc>3)
            file_headers=1;
        switch(string[i]){
            case 'v':
                number_of_strings=atoi(&string[2]);
                if(number_of_strings<0)
                    number_of_strings=INT_MAX;
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
                            if(number_of_strings<0)
                                number_of_strings=INT_MAX;
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
                            if(number_of_strings<0)
                                number_of_strings=INT_MAX;
                            for (; i < number_of_smth; i++) {
                                if (string[i] < '0' || string[i] > '9') {
                                    print_options_error(string + 2 * sizeof(char), 1);
                                    exit(1);
                                }
                            }

                            if(argc>=2&&isdigit(names[2][0])) {
                                for (o = 0; o < strlen(names[2]); o++) {
                                    if (names[2][o] >= '0' && names[2][o] <= '9')
                                        continue;
                                    print_options_error(string, 8);
                                    exit(1);
                                }
                                number_of_strings = atoi(names[2]);
                                if(number_of_strings<0)
                                    number_of_strings=INT_MAX;
                                print_bfiles_options_after(names,file_headers,number_of_strings,argc,1);
                                exit(0);
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
                if(number_of_strings<0)
                    number_of_strings=INT_MAX;
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
                            if(number_of_strings<0)
                                number_of_strings=INT_MAX;
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
                            if(number_of_strings<0)
                                number_of_strings=INT_MAX;
                            for (; i < number_of_smth; i++) {
                                if (string[i] < '0' || string[i] > '9') {
                                    print_options_error(string + 2 * sizeof(char), 1);
                                    exit(1);
                                }
                            }

                            if(argc>=2&&isdigit(names[2][0])) {

                                for (o = 0; o < strlen(names[2]); o++) {
                                    if (names[2][o] >= '0' && names[2][o] <= '9')
                                        continue;
                                    print_options_error(string, 8);
                                    exit(1);
                                }
                                number_of_strings = atoi(names[2]);
                                if(number_of_strings<0)
                                    number_of_strings=INT_MAX;
                                print_bfiles_options_after(names,file_headers,number_of_strings,argc,1);
                                exit(0);
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
                    if(number_of_strings<0)
                        number_of_strings=INT_MAX;
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
                        if(number_of_strings<0)
                            number_of_strings=INT_MAX;

                        if(argc==3){
                            read_file_tobytes(STDIN_FILENO, number_of_strings,"stdin");
                            exit(0);
                        }
						if(argc>4)
							file_headers=1;
						else
							file_headers=0;
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
                    if(number_of_strings<0)
                        number_of_strings=INT_MAX;
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
                        if(number_of_strings<0)
                            number_of_strings=INT_MAX;


                        if(argc==3){
                            read_file_tostrings(STDIN_FILENO, number_of_strings,"stdin");
                            exit(0);
                        }

						if(argc>4)
							file_headers=1;
						else
							file_headers=0;
                        
						print_files_options_after(names,file_headers,number_of_strings,argc,1);
                        exit(0);
                    }
                    print_options_error(string,4);
                    exit(1);
                }
                break;
            case '-':
                if(string[i-1]=='-'&&string[i+1]=='\0'){
					if(argc>2){
                       number_of_strings=atoi(names[2]);
                        if(number_of_strings<0)
                            number_of_strings=INT_MAX;
                        print_files_options_after(names,1,number_of_strings,argc,0);
                       exit(0);
                    }

                    read_file_tostrings(STDIN_FILENO, DEFAULT_STRINGSNUM, "stdin");
                    exit(0);
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
    if(number_of_strings<0)
        number_of_strings=INT_MAX;
    print_files_options_after(names,file_headers,number_of_strings,argc,0);
}

int main(int argc, char **argv) {
    char return_value = 0;
    int descriptor;
    int options_status = 0;
    int i;

    if(argc == 1){
        read_file_tostrings(STDIN_FILENO, DEFAULT_STRINGSNUM, "stdin");

        exit (return_value);
    }
    for (i = 1; i < argc; i++){
        if(argv[i][0] == '-' ){
            if (argv[i][1]=='\0'){
                print_files_options_after(argv,1,DEFAULT_STRINGSNUM,argc,-1);
                options_status=1;
                continue;
            }
			if(strlen(argv[i])>255){
			    print_options_error(argv[i],10);
			    continue;
			}

            if(!options_status){
                options_procesing(argv[1],argv,argc);
                break;
            }
            continue;
        }
			if(strlen(argv[i])>255){
			    print_options_error(argv[i],10);
				exit(1);
			}

        options_status = 1;
        descriptor = open(argv[i],O_RDONLY);
        if(descriptor == -1){
            print_options_error(argv[i],7);
            return_value = 1;
            continue;
        }
        if (argc>2) {
            if(i!=1)
                write(STDOUT_FILENO, "\n", 1);
            write(STDOUT_FILENO, "======> ", 8);
            write(STDOUT_FILENO, argv[i], strlen(argv[i]));
            write(STDOUT_FILENO, " <======\n", 9);
        }
        read_file_tostrings(descriptor,DEFAULT_STRINGSNUM,"stdin");
    }
    exit (return_value);
}

