`timescale 1ns / 1ps

////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer:
//
// Create Date:   00:15:41 09/19/2017
// Design Name:   controller
// Module Name:   /home/sonya/Documents/ise/14.7/ISE_DS/lab1/test.v
// Project Name:  lab1
// Target Device:  
// Tool versions:  
// Description: 
//
// Verilog Test Fixture created by ISE for module: controller
//
// Dependencies:
// 
// Revision:
// Revision 0.01 - File Created
// Additional Comments:
// 
////////////////////////////////////////////////////////////////////////////////

module test;

	reg CLOCK;
	reg [1:0] MOD;
	wire [15:0] LEDs_strip;
	wire ok;

	controller uut (
		.CLOCK(CLOCK), 
		.MOD(MOD), 
		.LEDs_strip(LEDs_strip),
		.ok(ok)
	);

	initial begin
		CLOCK = 0;
		MOD = 3;
	end
	
	always begin
		# 1 CLOCK = ~CLOCK;
	end
	
	always begin
		# 250 MOD = MOD + 1;
	end
      
endmodule

