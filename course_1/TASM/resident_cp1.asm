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
	cmp ax,1239h
	jne rezid
	inc ax
	inc sp
	inc sp
	iret
rezid:

	cmp cs:flag,1
	je finish
	inc cs:flag
	push ax
	push bx 
	push ds

	mov bx, 40h
	mov ds, bx
	mov al, byte ptr ds:[17h]

	push cs
	pop ds
	cmp al,00000100b

jne bez_kombinazii

	in al,60h
	cmp al,01fh	
	jne ctrle
	call pech_ctrs

ctrle:
	cmp al,012h
	jne ctrlx
	cmp word ptr id,1
	jne ctrlx
	call pech_ctrle
	
ctrlx:
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
	mov id,1h
			mov bh,0
			mov ah,03h
			int 10h
			push dx	
				mov dx,0045h
				mov ah,02h
				int 10h
			mov ah,04h
			int 1ah 	
			mov al,dl
			xor ah,ah
					call symbolsout
				mov al,2eh
				int 29h
				mov al,dh
				xor ah,ah
					call symbolsout
				mov al,2eh
				int 29h
				mov ax,cx
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

symbolsout proc
	push ax
	push bx
	push cx
	push dx
		mov bx,10h
		xor cx,cx
		del:
		xor dx,dx
		div bx
		push dx
		inc cx
		or ax,ax
		jnz del
	viv: 
	pop ax
	add ax, 30h
	int 29h
	loop viv
	pop dx
	pop cx
	pop bx
	pop ax
	ret
symbolsout endp

pech_ctrle proc
	push ax
	push bx
	push cx
	push dx
	mov id,0
		mov bh,0
		mov ah,03h
		int 10h
		push dx
			mov bh,00
			mov dx,0045h
			mov ah, 02h
			int 10h
		mov cx,9h
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

pech_ctrle endp

pech_ctrlx proc

		push ds
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
