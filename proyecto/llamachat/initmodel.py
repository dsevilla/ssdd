from ctransformers import AutoModelForCausalLM

llm = AutoModelForCausalLM.from_pretrained("TheBloke/Llama-2-7b-Chat-GGUF",
                                           model_file="llama-2-7b-chat.Q4_K_M.gguf",
                                           model_type="llama",
                                           gpu_layers=0)

