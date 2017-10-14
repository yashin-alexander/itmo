`timescale 1ns / 1ps

module test;

	reg SCLK;
	reg [1:0] SW;
	wire [15:0] LD;

	controller uut (
		.SCLK(SCLK), 
		.SW(SW), 
		.LD(LD)
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
		# 64000 SW = SW + 1;	//1000
		# 32000 SW = SW + 1;	//freq = 500
		# 12800;	// freq = 200
		# 5000;	// freq = 200 and stop
		# 2000;	//wait for a 2000 times 
		
	end
      
endmodule

