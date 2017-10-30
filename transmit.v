`timescale 1ns / 1ps

module transmit(
input [7:0] word, 
input clk, 
input rst, 
output reg txd);

reg [7:0] transmissive_data;
reg [3:0] counter = 0;
reg ready = 1;

always @(posedge clk)
 if(ready)
	transmissive_data = word;

always @(posedge clk)
begin
	if (rst || counter == 8)
		begin
			txd <= 0;
			counter = 0;
			ready = 1;
		end
	else
		begin
			ready = 0;
			txd <= transmissive_data[7];
			transmissive_data = transmissive_data << 1;
			counter = counter + 1;
		end
end

endmodule
