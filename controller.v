`timescale 1ns / 1ps

/* system clock freq = 100 MHz => 100 000 000 clk\per second. 
50 000 000 by posedge. 50 000 000 / 96 00 = 5208.3(3) ~ 5208. 
*/
`define FREQ 10417

module controller(
input clk,
input SW,
input rst,
input rxd,
output reg txd,
output [7:0] word_1,
output freq_clk
);

reg [31:0] counter = 0;
reg freq_clk = 0;

echo_mode echo(
    .clk(freq_clk),
    .rst(rst),
    .SW(SW),
    .rxd(rxd),
	 .word(word_1),
	 .txd(txd_1)
);

message_mode uut (
        .clk(freq_clk), 
        .rst(rst), 
        .SW(SW), 
        .txd(txd_2),
        .word(word_2)
    );

always @(posedge clk)
		 begin
			 if (counter == 5208)
				  begin
						freq_clk = ~freq_clk;
						counter = 0;
				  end
			 counter = counter + 1;
		 end
		 
always @(posedge clk)
begin
		if (SW)
			begin
				txd <= txd_2;
			end
		else
			txd <= txd_1;
end
endmodule
