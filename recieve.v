`timescale 1ns / 1ps

module recieve(
input rxd, 
input clk, 
input rst, 
output reg[7:0] word);

reg [7:0] recieved_data = 0;
reg [3:0] counter = 0;

always @(posedge clk)
begin
	if (rst)
		begin
			recieved_data = 0;
			counter = 0;
			word <= 0;
		end
	else if (counter == 8 && rxd == 0)
		begin
			word <= recieved_data;
			counter = 0;
		end
	else 
		begin
			recieved_data <= recieved_data << 1;
			recieved_data[0] <= rxd;
			counter = counter + 1;
		end
end
endmodule
