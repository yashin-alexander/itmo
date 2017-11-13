`timescale 1ns / 1ps

module test_controller;

	// Inputs
	reg clk;
	reg SW;
	reg rst;
	reg rxd;

	// Outputs
	wire txd;
	wire [7:0] word;
	wire freq_clk;
	
	controller uut (
		.clk(clk), 
		.SW(SW), 
		.rst(rst),
		.rxd(rxd),
		.txd(txd),
		.word_1(word),
		.freq_clk(freq_clk)
	);

	initial begin
		clk = 0;
		SW = 0;
		rst = 0;
		rxd = 1;
	end

	 always begin
        # 1 clk = ~clk;
    end

	always begin
		# 1000000 SW = 1;
		# 1000000000 SW = 0;
	end
	
	always begin
        # 20832 rxd = 0;
        # 20832 rxd = 1;
		  # 166656 rxd = 0;
    end

endmodule

