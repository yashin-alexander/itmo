.model small
.stack 100h
.data
number_of_symbols_for_out db 0
object dw 0
power db 0
six_in_power dw 1

message0 db 10,13,'enter number of the nums',10,13,'$'
message db 10,13, 'Enter the number in a 6th-numeric system: $'
message1 db 10,13,'No such number in a 6th-numeric system',10,13,'$'
message2 db 10,13,'Result in 11th-numeric system: ',10,13,'$'
message5 db 10,13,'Incorrect number of symbols',10,13,'$'
.code
begin:
	mov ax,@data
	mov ds,ax
	mov ah,09h			
	lea dx,message0
	int 21h
	
H:	
	xor si,si
	mov ah,01h
	int 21h
	cmp al, 49		
	jb incorrect_number_length			
	cmp al, 53
	ja incorrect_number_length
	sub ax,30h		
	lea bx,object
	mov object,0		
	xor cx,cx		
	mov cl,al
	jmp show_message

incorrect_number_length:
	mov ah,09h
	lea dx,message5
	int 21h
	jmp H
	
show_message:
	mov ah,09h
	lea dx,message
	int 21h

read_number_in_the_cycle:
	xor ah,ah			
	inc ah
	int 21h

	cmp al, 48
	jb wrong
	cmp al, 53
	ja wrong

	sub al, 48


	mov si, ax
	mov ax, object
	mov dh,6	
	mul dh
	xor dh,dh

	add ax, si
	
	mov word ptr[object],ax



	loop read_number_in_the_cycle
	jmp print_result
	
wrong:
	mov ah,09h
	lea dx,message1
	int 21h
	jmp show_message

print_result:
	mov ah,09h
	lea dx,message2
	int 21h

	mov ax,object
	mov dl,11
	
push_into_the_stack:
	div dl
	mov bl,ah
	xor bh,bh			
	push bx


	inc number_of_symbols_for_out
	xor ah,ah			


	cmp al,0
	jne push_into_the_stack

	mov cl, number_of_symbols_for_out
	mov ah,02						
	
print_from_stack:
	pop dx
	add dl,48
	cmp dl,58
	je make_a_number

print_next:
	int 21h				
	loop print_from_stack		
	jmp exit
	
make_a_number:
	mov dl,65
	jmp print_next

exit:
	mov ax,4c00h
	int 21h
	end begin



