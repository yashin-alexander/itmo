`timescale 1ns / 1ps

module test;

	reg rxd;
	reg clk;
	reg rst;
	wire [7:0] word;

	recieve uut (
		.rxd(rxd), 
		.clk(clk), 
		.rst(rst), 
		.word(word)
	);

	initial begin
		rxd = 1;
		clk = 0;
		rst = 0;
	end
	
	always begin
		# 1 clk = ~clk;
	end
	
	always begin
		# 16 rxd = 0;
		# 20 rxd = 1;
	end
	
	always begin
		# 200 rst = 1;
	end
      
endmodule

