`timescale 1ns / 1ps

module test;

	reg SCLK;
	reg [1:0] SW;
	wire [15:0] LD;
	wire mode_clock;

	controller uut (
		.SCLK(SCLK), 
		.SW(SW), 
		.LD(LD),
		.mode_clock(mode_clock)
	);

	initial begin
		SCLK = 0;
		SW = 0;
	end
	
	always begin
		# 1 SCLK = ~SCLK;
	end
	
	always begin
		# 1000 SW = SW + 1; //nothing
		# 64000 SW = SW + 1;	//1000 frequency
		# 32000 SW = SW + 1;	//500
		# 12800 SW = SW ;	//200
		# 5000 SW = SW;	//200 and stop
		# 2000;	//stop for 2000 
		
	end
      
endmodule

