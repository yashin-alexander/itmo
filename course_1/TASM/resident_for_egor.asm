CSEG segment 
assume cs:CSEG, ds:CSEG, es:CSEG, ss:CSEG
org 100h
start:

jmp setup

orig dd ?

tsr proc
	pushf
	cmp ax,2229h		;если 9 прерывание вызвала эта программа, то выполнится оригинальный обработчик
	jne rezid
	inc ax
	inc sp
	inc sp 				;iret убирает из стека 3 значения, поэтому дважды увеличиваем его
	iret
rezid:

	push ax
	push bx 
	push ds

	mov bx, 40h
	mov ds, bx				;по адресу 40:0017 в памяти находится kbflag
	mov al, byte ptr ds:[17h]
	and ax,000fh			;если нажат ctrl то 3 бит выставлен
	push cs
	pop ds
	cmp al,00000100b		;проверяем 3 бит

jne bez_kombinazii		; выход на оригинальный обработчик

ctrls:
	in al,60h			;проверка на нажатые ctrl+s
	cmp al,01fh	
	jne ctrlx
	call ochistka
	call vivod_sostoyaniya

ctrlx:					;проверка на нажатые ctrl+x
	cmp al,02dh
	jne bez_kombinazii
	call vigruzka

bez_kombinazii:				; ориг обработчик
	pop ds
	pop bx
	pop ax
	finish:
	call dword ptr cs:orig
iret

tsr endp

ochistka proc 					; процедура сотрет старые слова на нужном месте экрана
	push ax
	push bx
	push cx
	push dx
			mov bh,0 				;узнаем координаты курсора
			mov ah,03h
			int 10h
			push dx					;сохраняем их в стек
				mov bh,00 			;перемещаем курсор в нужное нам место 
				mov dx,0000h
				mov ah, 02h
				int 10h
			mov cx,99h 				;заполняем пробелами место 
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
	ret
	ochistka endp

vivod_sostoyaniya proc 			; процедура вывода состояния клавы
	push ax
	push bx 
	push ds
	push cx

	mov cx,0 				; cx будем использовать для вывода 'nothing'
	mov bx, 40h
	mov ds, bx				; обращаемся опять к kbflag
	mov al, byte ptr ds:[17h]
	push cs
	pop ds
	push ax
	push ax
	push ax

proverka_na_caps:						; на капс
	mov bl,al
	and bl, 01000000b
	cmp bl, 01000000b
	jne proverka_na_num
				mov bh,0 				;устанавливаем где печатать
				mov ah,03h
				int 10h
				push dx	
					mov dx,0125h		
					mov ah,02h
					int 10h
			push ax
		inc cx
		mov al,'c'
		int 29h
		mov al,'a'
		int 29h
		mov al,'p'
		int 29h
		mov al,'s'
		int 29h
			pop ax
				mov bh,0 				;возвращаем каретку на место
				pop dx
				mov ah,02h
				int 10h
proverka_na_num:						; на нам
	pop ax
	mov bl,al
	and bl, 00100000b
	cmp bl, 00100000b
	jne proverka_na_scroll
				mov bh,0 				;устанавливаем где печатать
				mov ah,03h
				int 10h
				push dx	
					mov dx,012bh		
					mov ah,02h
					int 10h
		inc cx
			push ax
			inc cx
			mov al,'n'
			int 29h
			mov al,'u'
			int 29h
			mov al,'m'
			int 29h
			pop ax
				mov bh,0 				;возвращаем каретку на место
				pop dx
				mov ah,02h
				int 10h
proverka_na_scroll:						; на скроллл
	pop ax
	mov bl,al
	and bl, 00010000b
	cmp bl, 00010000b
	jne nothing_p
				mov bh,0 				;устанавливаем где печатать
				mov ah,03h
				int 10h
				push dx	
					mov dx,0132h		
					mov ah,02h
					int 10h
		inc cx
		push ax
		inc cx
		mov al,'s'
		int 29h
		mov al,'c'
		int 29h
		mov al,'r'
		int 29h
		mov al,'o'
		int 29h
		mov al,'l'
		int 29h
		mov al,'l'
		int 29h
		pop ax
				mov bh,0 				;возвращаем каретку на место
				pop dx
				mov ah,02h
				int 10h
nothing_p:							;клавиши не нажаты
	pop ax
	cmp cx,0
	jne konez
			mov bh,0 				;устанавливаем где печатать
			mov ah,03h
			int 10h
			push dx	
				mov dx,0137h		
				mov ah,02h
				int 10h
		push ax
		inc cx
		mov al,'n'
		int 29h
		mov al,'o'
		int 29h
		mov al,'t'
		int 29h
		mov al,'h'
		int 29h
		mov al,'i'
		int 29h
		mov al,'n'
		int 29h
		mov al,'g'
		int 29h
		pop ax
			mov bh,0 				;возвращаем каретку на место
			pop dx
			mov ah,02h
			int 10h
konez:	
						
	pop cx
	pop ds
	pop bx
	pop ax
ret
vivod_sostoyaniya endp

vigruzka proc 				;выгрузка из памяти
	push ds					
	push es
	push dx
call ochistka
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
	pop dx
	pop es
	pop ds
ret
vigruzka endp				; конец резидентной части

setup: 
	mov ax,2229h			;проверка на загруженность в памяти
	int 9h					;если резидент не загружен, то обратимся к оригинальному обработчику
	cmp ax,222ah			;оригинальный обработчик не поменяет 2229 
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