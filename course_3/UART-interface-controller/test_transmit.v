`timescale 1ns / 1ps

module test_transmit;

    reg [7:0] word;
    reg clk;
    reg rst;
	 reg connection_status;

    wire txd;

    transmit uut (
        .word(word), 
        .clk(clk), 
        .rst(rst), 
		  .connection_status(connection_status),
        .txd(txd)
    );

    initial begin
        word = 129;
        clk = 0;
        rst = 0;
		  connection_status = 1;
    end
    
	 
	 
    always begin
        # 1 clk = ~clk;
   end
    
    always begin
        # 9 word = 1;
   end
endmodule

