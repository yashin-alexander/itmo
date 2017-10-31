`timescale 1ns / 1ps

module transmit(
input [7:0] word, 
input clk, 
input rst, 
input connection_status,
output transmit_ready,
output reg txd);

reg [7:0] transmissive_data;
reg [3:0] counter = 0;
reg transmit_ready = 1;

always @(posedge clk)
    if (transmit_ready)
        begin
            transmissive_data = word;
            transmit_ready = 0;
        end

always @(posedge clk)
begin
    if (rst)
        begin
            txd = 1;
            counter = 0;
            transmissive_data = 0;
            transmit_ready = 1;
        end
    else if (connection_status)
        begin
            if (counter == 8)
                begin
                    txd = 0;
                    counter = 0;
                    transmit_ready = 1;
                end
            else
                begin
                    txd <= transmissive_data[7];
                    transmissive_data = transmissive_data << 1;
                    counter = counter + 1;
                end
        end
    else
        begin
            txd = 1;
            counter = 0;
        end
end

endmodule
