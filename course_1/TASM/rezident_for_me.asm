CSEG segment 
assume cs:CSEG, ds:CSEG, es:CSEG, ss:CSEG
org 100h
start:

jmp setup

orig dd ?
id dw 0
flag db 0

tsr proc
	pushf
	cmp ax,1239h		;если 9 прерывание вызвала эта программа, то выполнится оригинальный обработчик
	jne rezid
	inc ax
	inc sp
	inc sp 				;iret убирает из стека 3 значения, поэтому дважды увеличиваем его
	iret
rezid:

	cmp cs:flag,1
	je finish
	inc cs:flag
	push ax
	push bx 
	push ds

	mov bx, 40h
	mov ds, bx				;по адресу 40:0017 в памяти находится kbflag
	mov al, byte ptr ds:[17h]
	;and ax,000fh			;если нажат ctrl то 3 бит выставлен
	push cs
	pop ds
	cmp al,00000100b		;проверяем 3 бит

jne bez_kombinazii		; выход на оригинальный обработчик

	in al,60h			;проверка на нажатые ctrl+s
	cmp al,01fh	
	jne ctrle
	call pech_ctrs

ctrle:					;проверка на нажатые ctrl+e
	cmp al,012h
	jne ctrlx
	cmp word ptr id,1
	jne ctrlx			;выполняется, если только висит дата
	call pech_ctrle
	
ctrlx:					;проверка на нажатые ctrl+x
	cmp al,02dh
	jne bez_kombinazii
	call pech_ctrlx
	
bez_kombinazii:
	pop ds
	pop bx
	pop ax
	dec cs:flag
	finish:
	call dword ptr cs:orig
iret

tsr endp

pech_ctrs proc
	push ax
	push bx
	push cx
	push dx
	mov id,1h						;id - показывает, была ли показана дата.	
			mov bh,0
			mov ah,03h
			int 10h
			push dx	
				mov dx,0045h		;устанавливаем где печатать
				mov ah,02h
				int 10h
			mov ah,04h 				;получили дату
			int 1ah 	
			mov al,dl				;день в al
			xor ah,ah
					call symbolsout
				mov al,2eh
				int 29h
				mov al,dh			;месяц в al
				xor ah,ah
					call symbolsout
				mov al,2eh
				int 29h
				mov ax,cx			;год в ax
					call symbolsout
			mov bh,0 			
			pop dx
			mov ah,02h
			int 10h
	pop dx
	pop cx
	pop bx	
	pop ax
	ret
pech_ctrs endp

symbolsout proc						;вывод даты
	push ax
	push bx
	push cx
	push dx
		mov bx,10h
		xor cx,cx
		del:				;деление на 10 числа, пока число >= 10
		xor dx,dx
		div bx
		push dx
		inc cx				;количество символов в числе cx
		or ax,ax
		jnz del
	viv: 
	pop ax 			;достаем последний разряд из стека и выводим	
	add ax, 30h
	int 29h
	loop viv		;вывод всего числа с использованием стека
	pop dx
	pop cx
	pop bx
	pop ax
	ret				;возврат к строке call
symbolsout endp

pech_ctrle proc
	push ax
	push bx
	push cx
	push dx
	mov id,0
		mov bh,0 				;узнаем координаты курсора
		mov ah,03h
		int 10h
		push dx					;сохраняем их в стек
			mov bh,00 			;перемещаем курсор в нужное нам место 
			mov dx,0045h
			mov ah, 02h
			int 10h
		mov cx,9h 		;заполняем пробелами место с датой
	probely:
		mov al,20h
		int 29h
		loop probely
	mov bh,0 					;возвращаемся к исходному месту курсора
	pop dx
	mov ah,02h
	int 10h	
	pop dx
	pop cx
	pop bx
	pop ax
		ret					;переход к стандартному обработчику

pech_ctrle endp

pech_ctrlx proc

		push ds					;выгрузка из памяти 
	push es
	push dx
		mov ax, word ptr cs:orig+2
		mov ds, ax
		mov dx, word ptr cs:orig
		mov ax,2509h
		int 21h
		mov es,cs:[2ch]
		mov ah,49h
		int 21h
		push cs
		pop es
		mov ah,49h
		int 21h
		call pech_ctrle
	pop dx
	pop es
	pop ds
ret
pech_ctrlx endp

setup: 
	mov ax,1239h			;проверка на загруженность в памяти
	int 9h					;если резидент не загружен, то обратимся к оригинальному обработчику
	cmp ax,123ah			;оригинальный обработчик не поменяет 1239 
	jz nosetup				;поэтому устанавливаем резидент

		mov ax,3509h
		int 21h
		mov word ptr orig,bx
		mov word ptr orig+2,es

		mov ax, 2509h
		lea dx, tsr
		int 21h
		
lea dx, setup
int 27h

nosetup:
mov ax,4c00h
int 21h

cseg ends
end start			
