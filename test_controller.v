`timescale 1ns / 1ps

module test_controller;

	// Inputs
	reg clk;
	reg SW;
	reg rst;
	reg rxd;

	// Outputs
	wire txd;
	wire freq_clk;
	wire counter;
	wire [7:0] word;

	
	controller uut (
		.clk(clk), 
		.SW(SW), 
		.rst(rst),
		.rxd(rxd),
		.txd(txd),
		.freq_clk(freq_clk),
		.counter(counter),
		.word_1(word)
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
        # 8336 rxd = 0;
        # 10430 rxd = 1;
    end

endmodule

