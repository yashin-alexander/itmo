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
lea bx, arr

forr:              
mov dl, 5       
mov ax, si      
mul dl		
mov dl, 3		
div dl
xor ah,ah
add ax, si	
mov [bx], ax
inc bx		 
inc si
	mov dl, 10
	xor ah, ah
	div dl
		mov cl, ah			
		cmp al, 0 			
		jz out1
			mov dl, al
			add dl, 30h
			mov ah, 02h
			int 21h
		out1:
		mov dl, cl
		add dl, 30h
		mov ah, 02h
		int 21h
	mov al, 20h
	int 29h					
cmp si, 26
jc  forr	
		mov al, 0dh 		
		int 29h
		mov al, 0ah
		int 29h

mov cx,25
xor bx,bx
xor di,di
xor si,si
form: 
	mov dl, byte ptr [arr+bx]
	and dx, 01h
	cmp dx,1

jne che
	inc si
	jmp skip	

	che:
	inc di
skip:
inc bx
loop form

mov dl, 10
	mov ax, di
	xor ah, ah
	div dl
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
int 29h

mov dl, 10
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
int 29h
	
xor ax,ax
xor bx,bx
xor dx,dx
xor cx,cx
mov dx,di
mov cx, si
lea di, brr
lea si, crr

mass:							
	mov al, byte ptr [arr+bx]	
	cmp ax,dx
		jle pro
			mov [di],al
			inc di
			inc q
	pro:
		cmp ax,cx
		jge pro1
			mov [si],al
			inc si
			inc i
	pro1:
		inc bx
		dec k
		cmp k,0
jne mass

mov di,0
perv:
	mov dl, 10 				
		mov al, byte ptr [brr+di]
		xor ah, ah
		div dl
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
		mov al, 20h
		int 29h					
	inc di 		
	dec q
	cmp q,0
jg perv
		mov al, 0dh
		int 29h
		mov al, 0ah
		int 29h

mov si,0
vtor:
	mov dl, 10 				
		mov al, byte ptr [crr+si]
		xor ah, ah
		div dl
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
jg vtor
	
mov ax, 4c01h 
int 21h	
code ends
end start