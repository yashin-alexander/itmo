`timescale 1ns / 1ps

module controller_test;

	// Inputs
	reg clk;
	reg rst;
	reg SW;
	reg rxd;

	// Outputs
	wire txd;
	wire [7:0] word;
	wire recieve_ready;
	
	// Instantiate the Unit Under Test (UUT)
	controller uut (
		.clk(clk), 
		.rst(rst), 
		.SW(SW), 
		.rxd(rxd), 
		.txd(txd),
		.word(word),
		.recieve_ready(recieve_ready)
	);

	initial begin

		clk = 0;
		rst = 0;
		SW = 0;
		rxd = 1;

	end
	
	always begin
		# 1 clk = ~clk;
	end
	
	always begin
		rxd = 1;
		# 2 rxd = 0;
		# 2 rxd = 1;
		# 2 rxd = 1;
		# 2 rxd = 1;
		# 2 rxd = 1;
		# 2 rxd = 0;
		# 2 rxd = 1;
		# 2 rxd = 0;
		# 2;
	end
      
endmodule

