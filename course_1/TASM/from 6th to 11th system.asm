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
	mov ax,@data			;помещаем в ах адрес сегмента данных 
	mov ds,ax				;в ds 
	mov ah,09h			
	lea dx,message0
	int 21h				;вывод приглашения 
	
H:	
	xor si,si			;null for si reg
	mov ah,01h			;функция, которая помещает значение ASCII-кода нажатой клавиши в регистр AL
	int 21h			;вызываем прерывание с номером 21h 
	cmp al, 49		
	jb incorrect_number_length			
	cmp al, 53
	ja incorrect_number_length			;Incorrect number of symbols case
	sub ax,30h		
	lea bx,object		;поместили адрес object в bx
	mov object,0		
	xor cx,cx		
	mov cl,al		;поместили в сl код считанного символа  cx: ch:________ cl:________
	jmp show_message

incorrect_number_length:			;если введен 0 как количество разрядов
	mov ah,09h
	lea dx,message5
	int 21h
	jmp H
	
show_message:
	mov ah,09h
	lea dx,message
	int 21h			;вывод сообщение о вводе разряда

read_number_in_the_cycle:
	xor ah,ah			
	inc ah    			;помещаем 1 в аh в два такта 
	int 21h				;считали символ 

	cmp al, 48
	jb wrong			;если символ, код которого ниже 0 в ascii table;
	cmp al, 53
	ja wrong			;если символ, код которого больше 5 в ascii table;

	sub al, 48


	mov si, ax
	mov ax, object
	mov dh,6	
	mul dh
	xor dh,dh

	add ax, si
	
	mov word ptr[object],ax



	loop read_number_in_the_cycle			;в cl лежит количество разрядов по которому мы итерируемся 
	jmp print_result
	
wrong:					;если введено некорректное число
	mov ah,09h
	lea dx,message1
	int 21h
	jmp show_message

print_result:
	mov ah,09h
	lea dx,message2
	int 21h			;вывод сообщения о резуьтате

	mov ax,object		;поместили в ax object
	mov dl,11			;
	
push_into_the_stack:
	div dl
	mov bl,ah			;остаток в bl 
	xor bh,bh			
	push bx			;записали остаток в stack


	inc number_of_symbols_for_out			;инкремент глобальной переменной
	xor ah,ah			


	cmp al,0				;сравнили ответ с 0
	jne push_into_the_stack			;если ответ не равен 0

	mov cl, number_of_symbols_for_out	;помещаем в регистр-счетчик глобальну переменную с количеством записанных в стек операндов 
	mov ah,02						
	
print_from_stack:
	pop dx				;забрать из стека то, что лежит сверху
	add dl,48			;получить ascii код цифры
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



