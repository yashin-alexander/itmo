`timescale 1ns / 1ps

module controller_test;

	// Inputs
	reg clk;
	reg rst;
	reg SW;
	// Outputs
	wire txd;
	wire [7:0] word;
	wire [9:0] counter;
	wire transmit_ready;
	
	// Instantiate the Unit Under Test (UUT)
	message_mode uut (
		.clk(clk), 
		.rst(rst), 
		.SW(SW), 
		.txd(txd),
		.word(word),
		.counter(counter),
		.transmit_ready(transmit_ready)
	);

	initial begin

		clk = 0;
		rst = 0;
		SW = 1;

	end
	
	always begin
		# 1 clk = ~clk;
	end

endmodule

