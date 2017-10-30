`timescale 1ns / 1ps
module controller(
input clk,
input rst,
input SW,
input rxd,
output txd,
output [7:0] word,
output recieve_ready
);

reg transmit_ready;
reg connection_status = 0;

recieve uut (
		.rxd(rxd), 
		.clk(clk), 
		.rst(rst), 
		.word(word),
		.recieve_ready(recieve_ready)
);
	
transmit uut1 (
		.word(word), 
		.clk(clk), 
		.rst(rst),
		.connection_status(connection_status),
		.txd(txd),
		.transmit_ready(transmit_ready)
);


always @(posedge clk)
		// echo
		if (!SW)
			if(recieve_ready)
				connection_status = 1;
			else if(transmit_ready)
				connection_status = 0;
endmodule
