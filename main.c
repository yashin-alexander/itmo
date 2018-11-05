#include "stm32f0xx.h"
#include "stm32f0xx_gpio.h"

typedef uint8_t (*check_cancellation_req_fn) (void);

void delay(void) {
    int i = 2000000;
    while(i--);
}


uint8_t user_button_pressed(void)
{
    return (GPIO_ReadInputData(GPIOD) & GPIO_Pin_2);
}


uint8_t user_button_released(void)
{
    return !user_button_pressed();
}


static
void gpio_toggle(GPIO_TypeDef* gpio_port, uint32_t gpio_pins)
{
    gpio_port->ODR ^= gpio_pins;
}


static
void gpio_configure_output(GPIO_TypeDef* gpio_port, uint16_t gpio_pin)
{
    GPIO_InitTypeDef gpio_init = {0};
    gpio_init.GPIO_Pin = gpio_pin;
    gpio_init.GPIO_Speed = GPIO_Speed_Level_3;
    gpio_init.GPIO_Mode = GPIO_Mode_OUT;
    GPIO_Init(gpio_port, &gpio_init);
}


static
void gpio_configure_input(GPIO_TypeDef* gpio_port, uint16_t gpio_pin)
{
    GPIO_InitTypeDef gpio_init = {0};
    gpio_init.GPIO_Pin = gpio_pin;
    gpio_init.GPIO_Speed = GPIO_Speed_Level_3;
    gpio_init.GPIO_Mode = GPIO_Mode_IN;
    GPIO_Init(gpio_port, &gpio_init);
}


static
void shift_animation_init(GPIO_TypeDef* gpio_port, uint32_t* pins, uint32_t pins_size)
{
    uint32_t initial_pins = pins[0] | pins[1] | pins[2];
    gpio_toggle(GPIOC, initial_pins);
}


static
void shift_right_animation(GPIO_TypeDef* gpio_port,
                           check_cancellation_req_fn cancel_requested,
                           uint32_t* pins,
                           uint32_t pins_size)
{
    for (uint8_t i = 0; i < pins_size ; i++) {
        delay();
        if (cancel_requested()) { return; }
            gpio_toggle(gpio_port, pins[i]);
            if (i == 0)
                gpio_toggle(gpio_port, pins[pins_size - 1]);
            else
                gpio_toggle(gpio_port, pins[i - 1]);
    }
}



static
void accumulation_animation_init(GPIO_TypeDef* gpio_port, uint32_t* pins, uint32_t pins_size)
{
    uint8_t last_idx = pins_size - 1;
    uint32_t initial_pins = pins[0] | pins[last_idx];
    gpio_toggle(gpio_port, initial_pins);
}


static
void accumulation_animation(GPIO_TypeDef* gpio_port,
                            check_cancellation_req_fn cancel_requested,
                            uint32_t* pins,
                            uint32_t pins_size)
{
    uint8_t frames_count = pins_size / 2 - 1;
    uint8_t last_idx = pins_size - 1;

    for (uint8_t i = 1; i <= frames_count; i++) {
        delay();
        if (cancel_requested()) { return; }
        uint32_t pins_for_toggle =
                pins[i - 1] |
                pins[i] |
                pins[last_idx - i + 1] |
                pins[last_idx -i];
        gpio_toggle(gpio_port, pins_for_toggle);
    }
}


int main(void)
{
    RCC->AHBENR |= RCC_AHBENR_GPIOCEN;
    RCC->AHBENR |= RCC_AHBENR_GPIODEN;
    delay();

    uint32_t pins_map[] = {
            GPIO_Pin_5,
            GPIO_Pin_8,
            GPIO_Pin_6,
            GPIO_Pin_9,
    };
    uint32_t pins_map_size = 4;

    // for animation (PC4 is busy)
    for (uint8_t i = 0; i < pins_map_size; i++) {
        gpio_configure_output(GPIOC, pins_map[i]);
    }

    // for user button
    gpio_configure_input(GPIOD, GPIO_Pin_2);

    for(;;) {
        if (user_button_pressed()) {
            GPIOC->ODR = 0;
            accumulation_animation_init(GPIOC, pins_map, pins_map_size);

            while (user_button_pressed()) {
                accumulation_animation(GPIOC,
                                       &user_button_released,
                                       pins_map,
                                       pins_map_size);
            }
        } else {
            GPIOC->ODR = 0;
            shift_animation_init(GPIOC, pins_map, pins_map_size);

            while (user_button_released()) {
                shift_right_animation(GPIOC,
                                      &user_button_pressed,
                                      pins_map,
                                      pins_map_size);
            }
        }
    }
}

