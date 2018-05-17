.286
org 100h 
data segment 
	arr dw 25 dup(0)
	brr dw 25 dup(0)
	crr dw 25 dup(0)
	k dw 25
	i dw 0
	q dw 0
	data ends 
code segment 
	assume cs:code, ds:data
start: 
mov ax, data 
mov ds, ax      

mov si, 1        
lea bx, arr     ;в bx записываем адрес начала массива

forr:              
mov dl, 5       
mov ax, si      
mul dl		
mov dl, 3		
div dl			;остаток попал в ah, целая часть попала в al 
xor ah,ah
add ax, si	
mov [bx], ax	;результат переносим в адрес, который записан в bx
inc bx		 
inc si 						;начало вывода массива
	mov dl, 10
	xor ah, ah
	div dl					;остаток в аl, целое в ah
		mov cl, ah			
		cmp al, 0 			
		jz out1
			mov dl, al		;если число было >=10
			add dl, 30h
			mov ah, 02h
			int 21h
		out1:				;для последнего разряда
		mov dl, cl
		add dl, 30h
		mov ah, 02h
		int 21h
	mov al, 20h				;пробел
	int 29h					
cmp si, 26					;пока результат отрицателен cf=1, потом 0
jc  forr	
		mov al, 0dh 		
		int 29h
		mov al, 0ah
		int 29h				;конец вывода массива

mov cx,25					;начало подсчета четных\нечетных
xor bx,bx
xor di,di
xor si,si
form: 
	mov dl, byte ptr [arr+bx]	;в dx записываем значение из начала массива
	and dx, 01h					;умножаем побитно, т.к. загрузилось сразу 2 числа
	cmp dx,1

jne che 					;используем флаг нуля. и усл переход
	inc si					;счетчик нечетных
	jmp skip	

	che: 					;формирование массива нечетных
	inc di					;счетчик четных 
skip:
inc bx
loop form					;конец подсчета четных\нечетных

mov dl, 10 					;вывод x1
	mov ax, di
	xor ah, ah
	div dl ; 
		mov cl, ah			
		cmp al, 0 			
			jz out2
				mov dl, al			
				add dl, 30h
				mov ah, 02h
				int 21h
			out2:				
			mov dl, cl
			add dl, 30h
			mov ah, 02h
			int 21h
		mov al, 20h		
		int 29h					
	mov al, 0dh 		
	int 29h
mov al, 0ah
int 29h						;конец вывода x1

mov dl, 10 					;вывод x2
	mov ax, si
	xor ah, ah
	div dl 	
		mov cl, ah			
		cmp al, 0 			
		jz out3
			mov dl, al			
			add dl, 30h
			mov ah, 02h
			int 21h
		out3:				
		mov dl, cl
		add dl, 30h
		mov ah, 02h
		int 21h					
	mov al, 0dh 		
	int 29h
mov al, 0ah
int 29h				;конец вывода x2
	
xor ax,ax
xor bx,bx
xor dx,dx
xor cx,cx
mov dx,di 			;нечетные в di
mov cx, si 			;четные в si
lea di, brr
lea si, crr			;записываем адреса начала массивов

mass:							
	mov al, byte ptr [arr+bx]	
	cmp ax,dx
		jle pro 				;если ax<17, переходим 
			mov [di],al			;создание массива "больше количесва четных" >X1
			inc di
			inc q 				;счетчик для вывода
	pro:
		cmp ax,cx
		jge pro1				;если ax>8, переходим 
			mov [si],al			;создание массива "меньше количесва нечетных" <X2
			inc si
			inc i 				;счетчик для вывода
	pro1:
		inc bx
		dec k
		cmp k,0
jne mass							; если k не=0 - переход

mov di,0 						;вывод "больше количества четных"
perv:
	mov dl, 10 				
		mov al, byte ptr [brr+di]
		xor ah, ah
		div dl ; 
			mov cl, ah			
			cmp al, 0 			
			jz out4
				mov dl, al			
				add dl, 30h
				mov ah, 02h
				int 21h
			out4:				
			mov dl, cl
			add dl, 30h
			mov ah, 02h
			int 21h
		mov al, 20h			;пробел
		int 29h					
	inc di 		
	dec q
	cmp q,0
jg perv
		mov al, 0dh 		;перенос строки
		int 29h
		mov al, 0ah
		int 29h					;конец вывода "больше количества четных"

mov si,0 		;вывод "меньше количества нечетных" (переход пока счетчик > 0)
vtor:
	mov dl, 10 				
		mov al, byte ptr [crr+si]
		xor ah, ah
		div dl ; 
			mov cl, ah			
			cmp al, 0 			
			jz out5
				mov dl, al			
				add dl, 30h
				mov ah, 02h
				int 21h
			out5:				
			mov dl, cl
			add dl, 30h
			mov ah, 02h
			int 21h
		mov al, 20h		
		int 29h					
	inc si 		
	dec i
	cmp i,0
jg vtor			;конец вывода "меньше количества нечетных" (переход пока счетчик > 0)
	
mov ax, 4c01h 
int 21h	
code ends
end start