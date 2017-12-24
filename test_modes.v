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
	wire word_on_line;
	wire connection_status;
	
	// Instantiate the Unit Under Test (UUT)
	echo_mode uut2 (
		.clk(clk), 
		.rst(rst), 
		.SW(SW), 
		.rxd(rxd),
		.txd(txd),
		.word(word),
		.word_on_line(word_on_line),
		.connection_status(connection_status),
		.transmit_ready(transmit_ready)
	);
	/*message_mode uut (
        .clk(clk), 
        .rst(rst), 
        .SW(SW), 
        .txd(txd),
        .word(word)
    );*/

	initial begin

		clk = 0;
		rst = 0;
		rxd = 1;
		SW = 0;

	end
	
	
	always begin
		# 1 clk = ~clk;
	end
	
	always begin
        # 50 rxd = 0;
        # 2 rxd = 1;
        # 16 rxd = 0;
		  # 2 rxd = 1;
    end

endmodule
