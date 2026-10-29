bits 32 ; assembling for the 32 bits architecture

; declare the EntryPoint (a label defining the very first instruction of the program)
global start        

; declare external functions needed by our program
extern exit               ; tell nasm that exit exists even if we won't be defining it
import exit msvcrt.dll    ; exit is a function that ends the calling process. It is defined in msvcrt.dll
                          ; msvcrt.dll contains exit, printf and all the other important C-runtime specific functions

; our data is declared here (the variables needed by our program)
segment data use32 class=data
    ; ...
  ;x=a+13-c+d-7+b, a  b c-byte
  ; 01 02 03 04
     ; a db 1
     ; b db 2
     ; c db 3
     ; d db 4
     ; x resb 1
     
   ; 01 00 02 00 03 00 04 00 00 00
     a dw 1
     b dw 2
     c dw 3
     d dw 4
     x resb 2
; our code starts here
segment code use32 class=code
    start:
        ; ...
          ;a+13
          mov al, [a]
          add al, 13 ; AL=a+13
          ;..-c
          sub al, [c]; AL=a+13-c
          ;...+d
          add al, [d]  ; AL=a+13-c+d
          sub al, 7     ; AL=a+13-c+d-7
          add al, b     ; AL=a+13-c+d-7+b
          ; stocam rezultatul in memorie
          mov [x], al
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
