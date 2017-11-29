`timescale 1ns / 1ps

module test_modes;
	// Inputs
	reg clk;
	reg rst;
	reg SW;
	reg rxd;
	// Outputs
	wire txd;
	wire [7:0] word;
	wire [9:0] counter;
	wire recieve_ready;
	wire transmit_ready;
	
	// Instantiate the Unit Under Test (UUT)
	/*echo_mode uut2 (
		.clk(clk), 
		.rst(rst), 
		.SW(SW), 
		.rxd(rxd),
		.txd(txd),
		.word(word),
		.recieve_ready(recieve_ready),
		.transmit_ready(transmit_ready)
	);*/
	message_mode uut (
        .clk(clk), 
        .rst(rst), 
        .SW(SW), 
        .txd(txd),
        .word(word)
    );

	initial begin

		clk = 0;
		rst = 0;
		rxd = 1;
		SW = 1;

	end
	
	
	always begin
		# 1 clk = ~clk;
	end
	
	/*always begin
        # 2 rxd = 0;
        # 2 rxd = 1;
        # 16 rxd = 0;
    end*/

endmodule
