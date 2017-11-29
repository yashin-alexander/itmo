`timescale 1ns / 1ps

/* system clock freq = 100 MHz => 100 000 000 clk\per second. 
50 000 000 by posedge. 50 000 000 / 96 00 = 10416.6(6) ~ 10417. 
*/
`define FREQ 9600

module controller(
input clk,
input SW,
input rst,
//input rxd,
output reg txd
//output [7:0] LD
);

reg [31:0] counter = `FREQ;
reg [31:0] sub_couter = 0;
reg freq_clk = 0;

/*echo_mode echo(
    .clk(freq_clk),
    .rst(rst),
    .SW(SW),
    .rxd(rxd),
	 .word(LD),
	 .txd(txd_1)
);*/


message_mode uut (
        .clk(freq_clk), 
        .rst(rst), 
        .SW(SW), 
        .txd(txd_2),
        .word()
    );

always @(posedge clk)
		 begin
			 if (counter == `FREQ)
				freq_clk = 1;
			 else if (counter == `FREQ/2)
				freq_clk = 0;	

			 if (counter == 0)
				counter = `FREQ;
			 else
				counter = counter - 1;
		 end
		 
always @(posedge clk)
begin
		txd <= txd_2;
		/*if (SW)
			begin
				txd <= txd_2;
			end
		else
			txd <= txd_1;*/
end
endmodule
