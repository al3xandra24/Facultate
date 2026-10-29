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
; 128+128
; our code starts here
segment code use32 class=code
    start:
        ; ...
          ;mov al, 128
          ;mov bl, 128
          ;add al, bl ; AL<- AL+BL
          mov ax, 128
          add ax, 128   ;AX=AX+128=256
          ; 10/3
            mov ax, 10
            mov bl, 3 ; AL=3
            div bl    ; AL=AX/BL, AH=AX%BL
          ; a+13-c+d-7+b
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
