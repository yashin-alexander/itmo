`timescale 1ns / 1ps

/* system clock freq = 100 MHz => 100 000 000 clk\per second. 
50 000 000 by posedge. 50 000 000 / 96 000 = 520.8(3) ~ 521. 
*/
`define FREQ 521

module controller(
input clk,
input SW,
input rst,
output rxd,
output txd,
output freq_clk,
output counter
);

reg [9:0] counter = 0;
reg freq_clk = 0;
reg [7:0] rxd = 0;
reg [7:0] txd = 0;

echo_mode echo(
    .clk(freq_clk),
    .rst(rst),
    .SW(SW),
    .rxd(rxd)
);

message_mode message(
    .clk(freq_clk),
    .rst(rst),
    .SW(SW),
    .txd(txd)
);

always @(posedge clk)
		 begin
			 if (counter == 521)
				  begin
						freq_clk = ~freq_clk;
						counter = 0;
				  end
			 counter = counter + 1;
		 end
endmodule
