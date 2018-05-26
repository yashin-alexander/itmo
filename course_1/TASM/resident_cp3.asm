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
	cmp ax,1239h
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

	in al,60h
	cmp al,01fh	
	jne ctrlx
	call vivod_sostoyaniya

ctrlx:
	cmp al,02dh
	jne bez_kombinazii
	call vigruzka
	
bez_kombinazii:
	pop ds
	pop bx
	pop ax
	
	call dword ptr cs:orig
iret

tsr endp

ochistka proc

	push cx
	push ax
	push bx
		mov bh,0
			mov ah,03h
			int 10h
			push dx	
			mov dx,0000h
			mov ah,02h
			int 10h
		mov cx, 80
	probely:
	mov al,20h
	int 29h
	loop probely

		mov bh,0
		pop dx
		mov ah,02h
		int 10h	
	pop bx
	pop ax
	pop cx
ret 
ochistka endp

vivod_sostoyaniya proc
	push ax
	push bx
	push cx
	push dx
		call ochistka
			mov bh,0
			mov ah,03h
			int 10h
			push dx	
				mov dx,0002h
				mov ah,02h
				int 10h
	pushf
	pop   ax
	and ax,8000h
	cmp  ax,8000h
	jne noviy_processor

stariy_processor:
	mov bx,0
	mov cx,30
mess1:
	mov ax, word ptr [less_bits+bx]
	shl ax,8
	cmp ah,2eh
	jne vivod1							
	mov cx,1
		vivod1:
		mov al,ah
		inc bx
		int 29h
	loop mess1

jmp disketa

noviy_processor:
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

disketa:
	int 11h
	and ax,01h
	cmp ax,01h
	jne no_disketa
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
no_disketa:
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
	int 10h

pop dx
pop cx
pop bx
pop ax
ret
vivod_sostoyaniya endp

vigruzka proc
	push ax
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
	pop ax
	ret
vigruzka endp


setup: 
	mov ax,1239h
	int 9h
	cmp ax,123ah
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
