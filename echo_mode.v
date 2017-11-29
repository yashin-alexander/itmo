`timescale 1ns / 1ps

module echo_mode(
input clk,
input rst,
input SW,
input rxd,
output txd,
output [7:0] word
);


reg connection_status = 0;


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
			if(word_on_line)			  // TRANSMIT 
				connection_status = 1;
			else if(transmit_ready)
				connection_status = 0;
endmodule