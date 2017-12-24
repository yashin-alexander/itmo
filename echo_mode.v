`timescale 1ns / 1ps

module echo_mode(
input clk,
input rst,
input SW,
input rxd,
output txd,
output [7:0] word,
output word_on_line,
output connection_status,
output transmit_ready
);


reg connection_status = 0;
reg [9:0] counter = 0;


recieve uut3 (
		.rxd(rxd), 
		.clk(clk), 
		.rst(rst), 
		.word(word),
		.word_on_line(word_on_line)
);
	
transmit uut2 (
		.word(word), 
		.clk(clk), 
		.rst(rst),
		.connection_status(connection_status),
		.txd(txd),
		.transmit_ready(transmit_ready)
);


always @(posedge clk)
		if (!SW)
			begin
				if(word_on_line)			  // TRANSMIT 
					begin
						connection_status = 1;
						counter = 0;
					end
				else if(transmit_ready && counter > 1)
					connection_status = 0;
				counter = counter + 1;
			end
endmodule