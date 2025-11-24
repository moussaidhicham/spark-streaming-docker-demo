import random
import time
import socket

host = '0.0.0.0'
port = 9999

print("Starting producer...")

while True:
    try:
        with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as s:
            s.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)
            s.bind((host, port))
            s.listen(5)
            print(f"Listening on {host}:{port}")

            while True:
                conn, addr = s.accept()
                print(f"Connected to {addr}")
                try:
                    while True:
                        num = random.randint(1, 100)
                        message = f"{num}\n"
                        conn.send(message.encode())
                        print(f"Sent: {num}")
                        time.sleep(1)
                except (ConnectionResetError, BrokenPipeError) as e:
                    print(f"Connection error: {e}")
                finally:
                    conn.close()
    except Exception as e:
        print(f"Error: {e}")
        time.sleep(1)