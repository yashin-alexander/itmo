CSEG segment 
assume cs:CSEG, ds:CSEG, es:CSEG, ss:CSEG
org 100h
start:

jmp setup

orig dd ?

tsr proc
	pushf
	cmp ax,2229h
	jne rezid
	inc ax
	inc sp
	inc sp
	iret
rezid:

	push ax
	push bx 
	push ds

	mov bx, 40h
	mov ds, bx
	mov al, byte ptr ds:[17h]
	and ax,000fh
	push cs
	pop ds
	cmp al,00000100b

jne bez_kombinazii

ctrls:
	in al,60h
	cmp al,01fh	
	jne ctrlx
	call ochistka
	call vivod_sostoyaniya

ctrlx:
	cmp al,02dh
	jne bez_kombinazii
	call vigruzka

bez_kombinazii:
	pop ds
	pop bx
	pop ax
	finish:
	call dword ptr cs:orig
iret

tsr endp

ochistka proc
	push ax
	push bx
	push cx
	push dx
			mov bh,0
			mov ah,03h
			int 10h
			push dx
				mov bh,00
				mov dx,0000h
				mov ah, 02h
				int 10h
			mov cx,99h
		probely:
			mov al,20h
			int 29h
			loop probely
		mov bh,0
		pop dx
		mov ah,02h
		int 10h	
	pop dx
	pop cx
	pop bx
	pop ax
	ret
	ochistka endp

vivod_sostoyaniya proc
	push ax
	push bx 
	push ds
	push cx

	mov cx,0
	mov bx, 40h
	mov ds, bx
	mov al, byte ptr ds:[17h]
	push cs
	pop ds
	push ax
	push ax
	push ax

proverka_na_caps:
	mov bl,al
	and bl, 01000000b
	cmp bl, 01000000b
	jne proverka_na_num
				mov bh,0
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
				mov bh,0
				pop dx
				mov ah,02h
				int 10h
proverka_na_num:
	pop ax
	mov bl,al
	and bl, 00100000b
	cmp bl, 00100000b
	jne proverka_na_scroll
				mov bh,0
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
				mov bh,0
				pop dx
				mov ah,02h
				int 10h
proverka_na_scroll:
	pop ax
	mov bl,al
	and bl, 00010000b
	cmp bl, 00010000b
	jne nothing_p
				mov bh,0
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
				mov bh,0
				pop dx
				mov ah,02h
				int 10h
nothing_p:
	pop ax
	cmp cx,0
	jne konez
			mov bh,0
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
			mov bh,0
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

vigruzka proc
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
vigruzka endp

setup: 
	mov ax,2229h
	int 9h
	cmp ax,222ah
	jz nosetup

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