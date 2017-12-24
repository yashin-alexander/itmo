`timescale 1ns / 1ps

module message_mode(
input clk,
input rst,
input SW,
output txd,
output [7:0] word
);

reg [95:0] string = "hello world!";
//reg [7:0] string = "L";
reg [32:0] counter = 0;
reg connection_status = 0;
reg [7:0] char = 0; 
	
transmit uut1 (
		.word(word), 
		.clk(clk), 
		.rst(rst),
		.connection_status(connection_status),
		.txd(txd),
		.transmit_ready(transmit_ready)
);

always @(posedge clk)
		if (SW)
			begin
				if (rst)
					begin
						string = "hello world!";
						//string = "L";
						connection_status = 0;
						counter = 0;
						char = 0;
					end
				if (counter > 12 && transmit_ready && counter < 15000)
					begin 
						connection_status = 0;
						counter = counter + 1;
					end
				else if (counter >= 15000)
					begin
						counter = 0;
						connection_status = 0;
						string = "hello world!";
						//string = "L";
					end
				else if (transmit_ready && counter < 13)
					begin
						connection_status = 1;
						char = string[95:88];
						string = string << 8;
						counter = counter + 1;
					end
			end

assign word = char;
//assign word = string;	
endmodule