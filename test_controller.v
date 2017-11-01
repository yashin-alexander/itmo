`timescale 1ns / 1ps

module test_controller;

	// Inputs
	reg clk;
	reg SW;
	reg rst;

	// Outputs
	wire [7:0] rxd;
	wire [7:0] txd;
	wire freq_clk;
	wire counter;
	
	controller uut (
		.clk(clk), 
		.SW(SW), 
		.rst(rst)
	);

	initial begin
		clk = 0;
		SW = 0;
		rst = 0;
	end

	 always begin
        # 1 clk = ~clk;
    end

	always begin
		# 100 SW = 1;
		# 100 SW = 0;
	end

endmodule

