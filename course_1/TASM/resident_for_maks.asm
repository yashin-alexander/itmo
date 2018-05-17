CSEG segment 
assume cs:CSEG, ds:CSEG, es:CSEG, ss:CSEG
org 100h
start:

jmp setup

orig dd ?

jmp begin
less_bits db '       16 bits in system.$'
more_bits db '       32 or more bits in system.$'
disketa_mess db ' Floppy inserted. $'
disketa_no db ' Floppy not inserted. $'

begin:

tsr proc
	pushf
	cmp ax,1239h		;если 9 прерывание вызвала эта программа, то выполнится оригинальный обработчик
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

	in al,60h			;проверка на нажатые ctrl+s
	cmp al,01fh	
	jne ctrlx
	call vivod_sostoyaniya

ctrlx:					;проверка на нажатые ctrl+x
	cmp al,02dh
	jne bez_kombinazii
	call vigruzka
	
bez_kombinazii:			; оригинальный обработчик
	pop ds
	pop bx
	pop ax
	
	call dword ptr cs:orig
iret

tsr endp

ochistka proc 					; процедурра очистки нужной части экрана

	push cx
	push ax
	push bx
		mov bh,0
			mov ah,03h
			int 10h
			push dx	
			mov dx,0000h		;устанавливаем где печатать
			mov ah,02h
			int 10h
		mov cx, 80
	probely:
	mov al,20h
	int 29h
	loop probely 				; заполняем пробелами

		mov bh,0 					;возвращаемся к исходному месту курсора
		pop dx
		mov ah,02h
		int 10h	
	pop bx
	pop ax
	pop cx
ret 
ochistka endp

vivod_sostoyaniya proc 			; вывод характеристик
	push ax
	push bx
	push cx
	push dx
		call ochistka
			mov bh,0
			mov ah,03h
			int 10h
			push dx	
				mov dx,0002h		;устанавливаем где печатать
				mov ah,02h
				int 10h
	pushf						; определим разрядность системы
	pop   ax
	and ax,8000h
	cmp  ax,8000h
	jne noviy_processor 		;  У 8086/8088-процессоров бит 15 регистра flags всегда установлен. У более "старших" процессоров он всегда сброшен. Поэтому если pushf/pop ax даёт в регистре ax установленный 15-й бит, это 16-битный процессор. 

stariy_processor: 				; вывод сообщения о 16бит системе
	mov bx,0
	mov cx,30
mess1:							; используем loop для вывода кучи симовлов
	mov ax, word ptr [less_bits+bx]		;обращаемся к символам в памяти адрес -less_bits
	shl ax,8 							;забираем слово, смещаем влево - получаем байт
	cmp ah,2eh 							;как только доходим до символа точки - заканчиваем печатать
	jne vivod1							
	mov cx,1 							;ставим эту самую точку, и выходим из loop
		vivod1:
		mov al,ah
		inc bx
		int 29h 						; выводим 29 прерыванием
	loop mess1

jmp disketa 							; переходим к состоянию дискеты

noviy_processor:						;если процессор новый делаем то же самое что и выше
	mov bx,0
	mov cx,40
mess2:
	mov ax, word ptr [more_bits+bx]
	shl ax,8
	cmp ah,2eh
	jne vivod2
	mov cx,1
		vivod2:
		mov al,ah
		inc bx
		int 29h
	loop mess2

disketa: 								; состояние дискеты
	int 11h								; int 11h оместит в первый бит ax 1, если флоппи вставлена
	and ax,01h
	cmp ax,01h
	jne no_disketa 						; опять аналогичный вывод символов ( как выше)
		mov bx,0
		mov cx,40
	mess3:
		mov ax, word ptr [disketa_mess+bx]
		shl ax,8
		cmp ah,2eh
		jne vivod3
		mov cx,1
			vivod3:
			mov al,ah
			inc bx
			int 29h
		loop mess3
		jmp konez
no_disketa:								; если дискеты нет
		mov bx,0
		mov cx,40
	mess4:
		mov ax, word ptr [disketa_no+bx]
		shl ax,8
		cmp ah,2eh
		jne vivod4
		mov cx,1
			vivod4:
			mov al,ah
			inc bx
			int 29h
		loop mess4

konez:
	mov bh,0 			
	pop dx
	mov ah,02h
	int 10h 			; вернули каретку на место

pop dx
pop cx
pop bx
pop ax
ret
vivod_sostoyaniya endp

vigruzka proc
	push ax
	push ds					;выгрузка из памяти 
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
	pop ax
	ret
vigruzka endp	 			;конец выгрузки


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
