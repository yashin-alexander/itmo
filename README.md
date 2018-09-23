# stm32-diode-animation
Diode animation example for stm32f3xx

- Download IDE [System Workbench for STM32](https://www.st.com/en/development-tools/sw4stm32.html)
- Clone and install flashing tool [st-link](https://github.com/texane/stlink)
- Create new C project
- Choose your board (for example STM32F3 Nucleo F303ZE)
- Choose hardware libary (StdPeriph)
- Replace `src/main.c` by `main.c` from this repo
- Build All
- Flash the STM with `st-flash write test.bin 0x8000000`
